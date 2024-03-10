package com.example.delivery;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
@Tag(name = "Delivery Management")
public class DeliveryController {

    private final DeliveryService service;

    @Operation(
            description = "Find all existing deliveries",
            summary = "This is a summary for find deliveries endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping
    public List<DeliveryDTO> findAll() {
        return service.findAll();
    }

    @Operation(
            description = "Find an existing delivery",
            summary = "This is a summary for find an existing delivery endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping("/{id}")
    public DeliveryDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(
            description = "Create a new delivery",
            summary = "This is a summary for create a new delivery endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO create(@RequestBody DeliveryDTO deliveryDTO) {
        return service.save(deliveryDTO);
    }

    @Operation(
            description = "Update an existing delivery",
            summary = "This is a summary for update an existing delivery endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @PutMapping
    public DeliveryDTO update(@RequestBody DeliveryDTO deliveryDTO) {
        return service.save(deliveryDTO);
    }


    @Operation(
            description = "Delete an existing delivery",
            summary = "This is a summary for delete an existing delivery endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
