FROM otel/opentelemetry-collector-contrib:0.122.1

# Still non-root (no apt-get)
ADD https://github.com/open-telemetry/opentelemetry-java-contrib/releases/download/v1.32.0/opentelemetry-jmx-metrics.jar \
    /otel/opentelemetry-java-contrib-jmx-metrics.jar

COPY scripts /scripts

USER 10001

CMD ["--config", "/etc/otelcol/config.yaml"]

