# span-not-end-demo

## Requirements

1. [OpenTelemetry javaAgent package](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/tag/v1.26.0)

## Quick Start

1. build the project

```bash
./mvnw clean package
```

2. Run application project

```bash
export OTEL_METRICS_EXPORTER=logging-otlp
export OTEL_TRACES_EXPORTER=logging-otlp
java -javaagent:/path/to/opentelemetry-javaagent.jar -jar application/target/application-1.0-SNAPSHOT.jar
```

3. Run springboot-gateway project

```bash
export OTEL_METRICS_EXPORTER=logging-otlp
export OTEL_TRACES_EXPORTER=logging-otlp
java -javaagent:/path/to/opentelemetry-javaagent.jar -jar springboot-gateway/target/springboot-gateway-1.0-SNAPSHOT.jar
```

4. Curl the api

```bash
curl http://localhost:8080/hello
```

## Result

The parent Span of `67262476246ee317` Span cannot find. Here are the trace data that applications
generated.

* The trace data that The application project generated

```json
{
  "scopeSpans": [
    {
      "scope": {
        "name": "io.opentelemetry.tomcat-7.0",
        "version": "1.26.0-alpha",
        "attributes": []
      },
      "spans": [
        {
          "traceId": "76770102d55d5811d07d379efba18996",
          "spanId": "67262476246ee317",
          "parentSpanId": "27227c8d7ccb2aae",
          "name": "GET /hello",
          "kind": 2,
          "startTimeUnixNano": "1685433879040657000",
          "endTimeUnixNano": "1685433884444587422",
          "attributes": [],
          "events": [],
          "links": [],
          "status": {}
        }
      ]
    },
    {
      "scope": {
        "name": "io.opentelemetry.spring-webmvc-3.1",
        "version": "1.26.0-alpha",
        "attributes": []
      },
      "spans": [
        {
          "traceId": "76770102d55d5811d07d379efba18996",
          "spanId": "9db5655329e5b26d",
          "parentSpanId": "67262476246ee317",
          "name": "HelloWorldController.sayHello",
          "kind": 1,
          "startTimeUnixNano": "1685433879372299542",
          "endTimeUnixNano": "1685433884442246562",
          "attributes": [],
          "events": [],
          "links": [],
          "status": {}
        }
      ]
    }
  ]
}
```

* The trace data that The springboot-gateway project generated

```json
{
  "scopeSpans": [
    {
      "scope": {
        "name": "io.opentelemetry.netty-4.1",
        "version": "1.26.0-alpha",
        "attributes": []
      },
      "spans": [
        {
          "traceId": "76770102d55d5811d07d379efba18996",
          "spanId": "746768c4dcdea939",
          "name": "GET",
          "kind": 2,
          "startTimeUnixNano": "1685433878429596000",
          "endTimeUnixNano": "1685433879150069546",
          "attributes": [],
          "events": [],
          "links": [],
          "status": {
            "code": 2
          }
        }
      ]
    },
    {
      "scope": {
        "name": "io.opentelemetry.spring-webflux-5.0",
        "version": "1.26.0-alpha",
        "attributes": []
      },
      "spans": [
        {
          "traceId": "76770102d55d5811d07d379efba18996",
          "spanId": "0a4ce1e28c8f4f1a",
          "parentSpanId": "746768c4dcdea939",
          "name": "FilteringWebHandler.handle",
          "kind": 1,
          "startTimeUnixNano": "1685433878549151408",
          "endTimeUnixNano": "1685433878929150391",
          "attributes": [],
          "events": [
            {
              "timeUnixNano": "1685433878927612390",
              "name": "exception",
              "attributes": [
                {
                  "key": "exception.message",
                  "value": {
                    "stringValue": "504 GATEWAY_TIMEOUT \"Response took longer than timeout: PT0.2S\"; nested exception is org.springframework.cloud.gateway.support.TimeoutException: Response took longer than timeout: PT0.2S"
                  }
                },
                {
                  "key": "exception.stacktrace",
                  "value": {
                    "stringValue": "org.springframework.web.server.ResponseStatusException: 504 GATEWAY_TIMEOUT \"Response took longer than timeout: PT0.2S\"; nested exception is org.springframework.cloud.gateway.support.TimeoutException: Response took longer than timeout: PT0.2S\n\tat org.springframework.cloud.gateway.filter.NettyRoutingFilter.lambda$filter$5(NettyRoutingFilter.java:202)\n\tSuppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: \nError has been observed at the following site(s):\n\t|_ checkpoint ⇢ org.springframework.cloud.gateway.filter.WeightCalculatorWebFilter [DefaultWebFilterChain]\n\t|_ checkpoint ⇢ HTTP GET \"/hello\" [ExceptionHandlingWebHandler]\nStack trace:\n\t\tat org.springframework.cloud.gateway.filter.NettyRoutingFilter.lambda$filter$5(NettyRoutingFilter.java:202)\n\t\tat reactor.core.publisher.Flux.lambda$onErrorMap$29(Flux.java:6571)\n\t\tat reactor.core.publisher.Flux.lambda$onErrorResume$30(Flux.java:6624)\n\t\tat reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:88)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.lambda$onError$3(TracingSubscriber.java:76)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.withActiveSpan(TracingSubscriber.java:97)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.withActiveSpan(TracingSubscriber.java:91)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.onError(TracingSubscriber.java:76)\n\t\tat reactor.core.publisher.SerializedSubscriber.onError(SerializedSubscriber.java:124)\n\t\tat reactor.core.publisher.FluxTimeout$TimeoutOtherSubscriber.onError(FluxTimeout.java:328)\n\t\tat reactor.core.publisher.Operators.error(Operators.java:196)\n\t\tat reactor.core.publisher.MonoError.subscribe(MonoError.java:52)\n\t\tat reactor.core.publisher.Mono.subscribe(Mono.java:4252)\n\t\tat reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.handleTimeout(FluxTimeout.java:294)\n\t\tat reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.doTimeout(FluxTimeout.java:273)\n\t\tat reactor.core.publisher.FluxTimeout$TimeoutTimeoutSubscriber.onNext(FluxTimeout.java:395)\n\t\tat reactor.core.publisher.StrictSubscriber.onNext(StrictSubscriber.java:89)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.lambda$onNext$1(TracingSubscriber.java:64)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.withActiveSpan(TracingSubscriber.java:97)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.withActiveSpan(TracingSubscriber.java:91)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.onNext(TracingSubscriber.java:64)\n\t\tat reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onNext(FluxOnErrorResume.java:73)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.lambda$onNext$1(TracingSubscriber.java:64)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.withActiveSpan(TracingSubscriber.java:97)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.withActiveSpan(TracingSubscriber.java:91)\n\t\tat io.opentelemetry.javaagent.shaded.instrumentation.reactor.v3_1.TracingSubscriber.onNext(TracingSubscriber.java:64)\n\t\tat reactor.core.publisher.MonoDelay$MonoDelayRunnable.run(MonoDelay.java:117)\n\t\tat reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:68)\n\t\tat reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:28)\n\t\tat java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)\n\t\tat java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)\n\t\tat java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)\n\t\tat java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)\n\t\tat java.base/java.lang.Thread.run(Thread.java:834)\nCaused by: org.springframework.cloud.gateway.support.TimeoutException: Response took longer than timeout: PT0.2S\n"
                  }
                },
                {
                  "key": "exception.type",
                  "value": {
                    "stringValue": "org.springframework.web.server.ResponseStatusException"
                  }
                }
              ]
            }
          ],
          "links": [],
          "status": {
            "code": 2
          }
        }
      ]
    }
  ]
}
```
