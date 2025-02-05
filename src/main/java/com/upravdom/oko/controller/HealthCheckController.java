package com.upravdom.oko.controller;

import com.upravdom.oko.HealthCheckResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер для проверки работоспособности приложения.
 */
@RestController
@Tag(name = "Health Check", description = "Эндпоинты для проверки работоспособности приложения")
public class HealthCheckController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    /**
     * Эндпоинт для проверки работоспособности приложения.
     * <p>
     * Возвращает ответ со статусом 200 и телом в формате JSON, содержащим имя приложения, версию и статус.
     *
     * @return ответ, содержащий имя приложения, версию и статус
     */
    @GetMapping("/health")
    @Operation(summary = "Проверка работоспособности приложения", description = "Возвращает статус приложения и версию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Приложение работает",
                    content = @Content(schema = @Schema(implementation = HealthCheckResponse.class)))
    })
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("appName", appName);
        response.put("appVersion", appVersion);
        return ResponseEntity.ok(response);
    }
}