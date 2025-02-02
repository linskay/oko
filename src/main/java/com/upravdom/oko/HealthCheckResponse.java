package com.upravdom.oko;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Ответ на запрос проверки работоспособности приложения")
public class HealthCheckResponse {
    @Schema(description = "Статус приложения", example = "UP")
    private String status;
    @Schema(description = "Название приложения", example = "${spring.application.name}")
    private String appName;
    @Schema(description = "Версия приложения", example = "${build.version}")
    private String appVersion;

    public HealthCheckResponse(String status, String appName, String appVersion) {
        this.status = status;
        this.appName = appName;
        this.appVersion = appVersion;
    }

    public HealthCheckResponse() {
    }
}