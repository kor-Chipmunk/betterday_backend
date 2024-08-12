package com.mashup.feelring.diary.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.mashup.feelring.PrivacyEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContentConverterTest {

    @InjectMocks
    private ContentConverter contentConverter;

    @Mock
    private PrivacyEncryptor privacyEncryptor;

    @Test
    void convertToDatabaseColumn() throws Exception {
        final String privacyData = "사적이고 민감한 데이터입니다.";
        final String expected = "암호화한 데이터";

        when(privacyEncryptor.encrypt(privacyData)).thenReturn(expected);

        final String encrypted = this.contentConverter.convertToDatabaseColumn(privacyData);

        assertEquals(expected, encrypted);
    }

    @Test
    void convertToEntityAttribute() throws Exception {
        final String encrypted = "암호화한 데이터";
        final String expected = "사적이고 민감한 데이터입니다.";

        when(privacyEncryptor.decrypt(encrypted)).thenReturn(expected);

        final String actual = this.contentConverter.convertToEntityAttribute(encrypted);

        assertEquals(expected, actual);
    }
}
