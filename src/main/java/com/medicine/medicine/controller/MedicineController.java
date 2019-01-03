package com.medicine.medicine.controller;

import com.medicine.medicine.dto.DrugstoreDto;
import com.medicine.medicine.dto.MedicineDto;
import com.medicine.medicine.service.DrugstoreService;
import com.medicine.medicine.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicineController {
    private final MedicineService medicineService;
    private final DrugstoreService drugstoreService;

    @Autowired
    public MedicineController(MedicineService medicineService, final DrugstoreService drugstoreService) {
        this.medicineService = medicineService;
        this.drugstoreService = drugstoreService;
    }

    @PostMapping("/create")
    public ResponseEntity createMedicine(@RequestBody final MedicineDto medicineDto) {
        medicineService.creatMedicine(medicineDto);
        return new ResponseEntity<>("Medicine created", HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public List<MedicineDto> getAllMedicine() {
        return medicineService.getMedicineList();
    }

    @GetMapping("search")
    public List<DrugstoreDto> searchDrugstoreByMedicine(@RequestParam(value = "medicine") final String medicine) {
        return drugstoreService.searchDrugstoreByMedicine(medicine);
    }

//    @GetMapping("/test")
//    public String getTest () {
//        return "test";
//    }
}
