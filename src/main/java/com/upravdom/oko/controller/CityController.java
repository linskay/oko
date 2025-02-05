package com.upravdom.oko.controller;

import com.upravdom.oko.dto.CityCreateDTO;
import com.upravdom.oko.dto.CityDTO;
import com.upravdom.oko.entity.City;
import com.upravdom.oko.exception.TraineeNotFoundException;
import com.upravdom.oko.repositoriy.CityRepository;
import com.upravdom.oko.service.CityService;
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
@RestController
@RequestMapping("/cities")
@Tag(name = "City Management", description = "API for managing cities")
@Validated
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * Получение списка всех городов.
     *
     * @return Список городов в формате CityDTO
     */
    @Operation(
            summary = "Получить список всех городов",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список городов успешно получен",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))})
            }
    )
    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities().stream().map(this::convertToDTO).toList());
    }

    /**
     * Получение города по ID.
     *
     * @param id Идентификатор города
     * @return Город в формате CityDTO
     */
    @Operation(
            summary = "Получить город по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Город успешно найден",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        return ResponseEntity.ok(convertToDTO(cityService.getCityById(id)));
    }

    /**
     * Добавление нового города.
     *
     * @param cityCreateDTO Данные нового города
     * @return Созданный город в формате CityDTO
     */
    @PostMapping
    @Operation(
            summary = "Добавить новый город",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Город успешно создан",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Неверные данные для создания города")
            }
    )
    public ResponseEntity<CityDTO> addCity(@RequestBody @Valid CityCreateDTO cityCreateDTO) {
        City createdCity = cityService.addCity(cityCreateDTO);
        CityDTO responseDTO = convertToDTO(createdCity);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Обновление существующего города.
     *
     * @param id      Идентификатор города
     * @param cityDTO Обновленные данные города
     * @return Обновленный город в формате CityDTO
     */
    @Operation(
            summary = "Обновить город",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Город успешно обновлен",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Город не найден"),
                    @ApiResponse(responseCode = "400", description = "Неверные данные для обновления города")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id,
                                              @RequestBody @Valid CityDTO cityDTO) {
        return ResponseEntity.ok(convertToDTO(cityService.updateCity(id, convertToEntity(cityDTO))));
    }

    /**
     * Удаление города по ID.
     *
     * @param id Идентификатор города
     */
    @Operation(
            summary = "Удалить город",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Город успешно удален"),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    private CityDTO convertToDTO(City city) {
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setCityName(city.getCityName());
        return dto;
    }

    private City convertToEntity(CityDTO dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setCityName(dto.getCityName());
        return city;
    }
}