package by.academy.pharmacy_spring_boot.controllers.rest;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.services.interfaces.MnnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.academy.pharmacy_spring_boot.constants.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MNN_REST)
public class MnnRestController {
    private final MnnService mnnService;

    @GetMapping()
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<MnnDto> findMnn() {
        return mnnService.findAllMnn();
    }

    @GetMapping(value = ID1)
    public ResponseEntity<MnnDto> getMnn(@PathVariable(ID) Integer id) {
        MnnDto mnnById = mnnService.findMnnById(id);
        if (mnnById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mnnById, HttpStatus.OK);
    }

}
