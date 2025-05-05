package com.app.productserv.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.*;

@Service
public class MetricsService {

    private final CloudWatchClient cloudWatchClient = CloudWatchClient.create();
    CloudWatchLogsClient logsClient = CloudWatchLogsClient.create();
    String logGroup = "my-local-app-logs";
    String logStream = "local-stream";

    public void publishLoginMetric(int count) {

        System.out.println("AWS Region: " + System.getProperty("aws.region"));
        System.out.println("AWS Access Key ID: " + System.getProperty("aws.accessKeyId"));

        MetricDatum datum = MetricDatum.builder()
                .metricName("LoginAttempts")
                .unit(StandardUnit.COUNT)
                .value((double) count)
                .dimensions(Dimension.builder()
                        .name("Environment")
                        .value("Local")
                        .build())
                .build();

        PutMetricDataRequest request = PutMetricDataRequest.builder()
                .namespace("MyAppMetrics")
                .metricData(datum)
                .build();

        cloudWatchClient.putMetricData(request);
        System.out.println("💥 Metric sent to CloudWatch!");


        // Ensure log group and stream exist
        try {
            logsClient.createLogGroup(CreateLogGroupRequest.builder().logGroupName(logGroup).build());
        } catch (ResourceAlreadyExistsException ignored) {}
        try {
            logsClient.createLogStream(CreateLogStreamRequest.builder()
                    .logGroupName(logGroup)
                    .logStreamName(logStream)
                    .build());
        } catch (ResourceAlreadyExistsException ignored) {}

        // Get next sequence token
        DescribeLogStreamsResponse streamResponse = logsClient.describeLogStreams(r -> r.logGroupName(logGroup));
        String token = streamResponse.logStreams().get(0).uploadSequenceToken();

        // Send a log event
        InputLogEvent logEvent = InputLogEvent.builder()
                .message("Hello from local Java app")
                .timestamp(System.currentTimeMillis())
                .build();

        PutLogEventsRequest putLogRequest = PutLogEventsRequest.builder()
                .logGroupName(logGroup)
                .logStreamName(logStream)
                .logEvents(logEvent)
                .sequenceToken(token)
                .build();

        logsClient.putLogEvents(putLogRequest);
        System.out.println("Log sent to CloudWatch.");
    }
}

