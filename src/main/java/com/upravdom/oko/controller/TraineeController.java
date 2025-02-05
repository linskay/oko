package com.upravdom.oko.controller;

import com.upravdom.oko.dto.TraineeDTO;
import com.upravdom.oko.entity.City;
import com.upravdom.oko.entity.Trainee;
import com.upravdom.oko.service.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainees")
@Tag(name = "Trainee Management", description = "API for managing trainees")
@Validated
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    /**
     * Получение списка всех стажеров.
     *
     * @return Список стажеров в формате TraineeDTO
     */
    @Operation(
            summary = "Получить список всех стажеров",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список стажеров успешно получен",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class))})
            }
    )
    @GetMapping
    public ResponseEntity<List<TraineeDTO>> getAllTrainees() {
        return ResponseEntity.ok(traineeService.getAllTrainees().stream().map(this::convertToDTO).toList());
    }

    /**
     * Получение стажера по ID.
     *
     * @param id Идентификатор стажера
     * @return Стажер в формате TraineeDTO
     */
    @Operation(
            summary = "Получить стажера по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Стажер успешно найден",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Стажер не найден")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(@PathVariable Long id) {
        return ResponseEntity.ok(convertToDTO(traineeService.getTraineeById(id)));
    }

    /**
     * Добавление нового стажера.
     *
     * @param traineeDTO Данные нового стажера
     * @return Созданный стажер в формате TraineeDTO
     */
    @Operation(
            summary = "Добавить нового стажера",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Стажер успешно создан",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Неверные данные для создания стажера")
            }
    )
    @PostMapping
    public ResponseEntity<TraineeDTO> addTrainee(@RequestBody @Valid TraineeDTO traineeDTO) {
        return new ResponseEntity<>(convertToDTO(traineeService.addTrainee(convertToEntity(traineeDTO))), HttpStatus.CREATED);
    }

    /**
     * Обновление существующего стажера.
     *
     * @param id          Идентификатор стажера
     * @param traineeDTO  Обновленные данные стажера
     * @return Обновленный стажер в формате TraineeDTO
     */
    @Operation(
            summary = "Обновить стажера",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Стажер успешно обновлен",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Стажер не найден"),
                    @ApiResponse(responseCode = "400", description = "Неверные данные для обновления стажера")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TraineeDTO> updateTrainee(@PathVariable Long id, @RequestBody @Valid TraineeDTO traineeDTO) {
        return ResponseEntity.ok(convertToDTO(traineeService.updateTrainee(id, convertToEntity(traineeDTO))));
    }

    /**
     * Удаление стажера по ID.
     *
     * @param id Идентификатор стажера
     */
    @Operation(
            summary = "Удалить стажера",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Стажер успешно удален"),
                    @ApiResponse(responseCode = "404", description = "Стажер не найден")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable Long id) {
        traineeService.deleteTrainee(id);
        return ResponseEntity.noContent().build();
    }

    private TraineeDTO convertToDTO(Trainee trainee) {
        TraineeDTO dto = new TraineeDTO();
        dto.setId(trainee.getId());
        dto.setFirstName(trainee.getFirstName());
        dto.setLastName(trainee.getLastName());
        dto.setCityId(trainee.getCity().getId());
        return dto;
    }

    private Trainee convertToEntity(TraineeDTO dto) {
       Trainee trainee = new Trainee();
        trainee.setId(dto.getId());
        trainee.setFirstName(dto.getFirstName());
        trainee.setLastName(dto.getLastName());
        if (dto.getCityId() != null) {
            City city = new City();
            city.setId(dto.getCityId());
            trainee.setCity(city);
        }
        return trainee;
    }
}