package ru.ainurminibaev.db.service.impl;

import javax.imageio.ImageIO;
import javax.swing.table.TableModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.common.collect.Lists;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.model.ChartResult;
import ru.ainurminibaev.db.model.ChartTask;
import ru.ainurminibaev.db.model.enums.ChartType;
import ru.ainurminibaev.db.repository.ChartResultRepository;
import ru.ainurminibaev.db.repository.ChartTaskRepository;
import ru.ainurminibaev.db.service.ChartProcessor;
import ru.ainurminibaev.db.service.DatabaseReader;
import ru.ainurminibaev.db.service.DatabaseReaderSelector;

/**
 * Created by ainurminibaev on 20.05.16.
 */
@Service
public class ChartProcessorImpl implements ChartProcessor {

    @Autowired
    ChartTaskRepository chartTaskRepository;

    @Autowired
    ChartResultRepository chartResultRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    DatabaseReaderSelector databaseReaderSelector;

    @Autowired
    private ObjectFactory<Cloudinary> cloudinaryObjectFactory;

    @Override
    @Scheduled(fixedDelay = 60 * 1000)
    public void makeReports() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        List<ChartTask> chartTasks = chartTaskRepository.findAll();
        for (ChartTask chartTask : chartTasks) {
            Date nowDate = new Date();
            long now = nowDate.getTime();
            if (now - chartTask.getLastUpdate() > chartTask.getUpdateTime()) {
                DatabaseReader dbReader = databaseReaderSelector.getDbReader(chartTask.getDbId());
                try {
                    TableModel reportData = dbReader.getDataForReport(chartTask.getSql());
                    ChartResult chartResult = new ChartResult();
                    chartResult.setChartId(chartTask.getId());
                    chartResult.setDataTable(reportData);
                    chartResult.setExecutionTime(System.currentTimeMillis() - now);
                    String chartName = "";
                    String chartImageUrl = "";
                    JFreeChart chart = null;
                    if (chartTask.getChartType() == ChartType.COUNT_ALL_PIE) {
                        DefaultPieDataset dataset = new DefaultPieDataset();
                        for (int i = 0; i < reportData.getRowCount(); i++) {
                            String key = reportData.getValueAt(i, 0).toString();
                            Long val = Long.valueOf(reportData.getValueAt(i, 1).toString());
                            dataset.setValue(key, val);
                        }
                        chartName = "Pie Chart: " + chartTask.getName() + " from " + dateFormat.format(nowDate);
                        chart = ChartFactory.createPieChart(
                                chartName,  // chart title
                                dataset,             // data
                                true,               // include legend
                                true,
                                false
                        );
                    }
                    if (chart != null) {
                        BufferedImage chartBufferedImage = chart.createBufferedImage(600, 800);
                        try {
                            //TODO counts and file temp path
                            Cloudinary cloudinary = cloudinaryObjectFactory.getObject();
                            String path = "/Users/ainurminibaev/Documents/Develop/static/diplom/temp.png";
                            FileOutputStream saveFileStream = new FileOutputStream(path);
                            ImageIO.write(chartBufferedImage, "png", saveFileStream);
                            Map uploadResult = cloudinary.uploader().upload(path, ObjectUtils.emptyMap());
                            chartImageUrl = uploadResult.get("url").toString();
                            chartResult.setImageLink(chartImageUrl);
                            new File(path).delete();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sendSlackMessage(chartTask, chartResult, chartName, chartImageUrl);
                    }
                    chartResultRepository.save(chartResult);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                chartTask.setLastUpdate(now);
                chartTaskRepository.save(chartTask);
            }
        }
    }

    private void sendSlackMessage(ChartTask chartTask, ChartResult chartResult, String chartName, String url) {
        Long now = new Date().getTime();
        Long lastSend = chartTask.getSlackLastSendTime();
        if (Boolean.TRUE.equals(chartTask.getEnableSlackNotification()) && (now - lastSend > chartTask.getSlackSendTime())) {
            SlackApi api = new SlackApi(chartTask.getSlackWebhookUrl());
            SlackMessage message = new SlackMessage("DbSchema Bot", "Chart Report for your " + chartName);
            SlackAttachment slackAttachment = new SlackAttachment().setImageUrl(url);
            slackAttachment.setFallback(chartName);
            message.setAttachments(Lists.newArrayList(slackAttachment));
            api.call(message);
            chartTask.setSlackLastSendTime(now);
        }
    }
}
