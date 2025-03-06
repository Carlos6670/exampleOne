package com.example.exampleOne;

import com.example.exampleOne.bo.DataBo;
import com.example.exampleOne.eo.DataEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DataBoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testConstructorAndGetters() {
        DataBo dataBo = new DataBo(1L, "Juan", 30);

        assertNotNull(dataBo);
        assertEquals(1L, dataBo.getId());
        assertEquals("Juan", dataBo.getName());
        assertEquals(30, dataBo.getEdad());
    }

    @Test
    void testNoArgsConstructor() {
        DataBo dataBo = new DataBo();

        assertNotNull(dataBo);
        assertNull(dataBo.getId());
        assertNull(dataBo.getName());
        assertEquals(0, dataBo.getEdad());
    }

    @Test
    void testSetters() {
        DataBo dataBo = new DataBo();
        dataBo.setId(2L);
        dataBo.setName("Maria");
        dataBo.setEdad(25);

        assertEquals(2L, dataBo.getId());
        assertEquals("Maria", dataBo.getName());
        assertEquals(25, dataBo.getEdad());
    }
    @Test
    void testValidationConstraints() {
        DataBo entity = new DataBo();

        // Prueba de restricción de nombre (menor de 3 caracteres)
        entity.setName("Jo");
        Set<ConstraintViolation<DataBo>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty(), "Se esperaba una violación de validación para nombre < 3 caracteres");

        // Prueba de restricción de nombre (mayor de 50 caracteres)
        entity.setName("JohnDoeJohnDoeJohnDoeJohnDoeJohnDoeJohnDoeJohnDoe");
        violations = validator.validate(entity);
        assertFalse(violations.isEmpty(), "Se esperaba una violación de validación para nombre > 50 caracteres");

        // Prueba de restricción de edad (menor de 1)
        entity.setEdad(0);
        violations = validator.validate(entity);
        assertFalse(violations.isEmpty(), "Se esperaba una violación de validación para edad < 1");

        // Prueba de restricción de edad (mayor de 120)
        entity.setEdad(121);
        violations = validator.validate(entity);
        assertFalse(violations.isEmpty(), "Se esperaba una violación de validación para edad > 120");
    }
}