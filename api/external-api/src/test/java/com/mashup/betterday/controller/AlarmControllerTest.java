package com.mashup.betterday.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mashup.betterday.AlarmCreateUsecase;
import com.mashup.betterday.DiaryReadUsecase;
import com.mashup.betterday.alarm.model.Alarm;
import com.mashup.betterday.alarm.model.AlarmId;
import com.mashup.betterday.common.link.model.MusicLink;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.model.alarm.AlarmCreateRequest;
import com.mashup.betterday.user.model.UserId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AlarmControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AlarmCreateUsecase alarmCreateUsecase;

    @Mock
    private DiaryReadUsecase diaryReadUsecase;

    @InjectMocks
    private AlarmController alarmController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alarmController).build();
    }

    @Test
    void testCreateAlarm() throws Exception {
        // Given
        UUID testUUID = UUID.randomUUID();
        MusicLink testMusicLink = new MusicLink("https://xxx.com/a.mp3");

        AlarmCreateRequest request = new AlarmCreateRequest();
        request.setDiaryUid(testUUID.toString());
        request.setLink(testMusicLink.getLink());

        Diary diary = Diary.write(
                new DiaryId(1L, testUUID),
                new Content("내용"),
                new UserId(1L),
                Weather.SUNNY
        );

        Alarm alarm = Alarm.register(
                new AlarmId(1L),
                testMusicLink,
                diary.getId()
        );

        when(diaryReadUsecase.read(testUUID.toString())).thenReturn(diary);
        when(alarmCreateUsecase.create(any(AlarmCreateUsecase.Request.class))).thenReturn(alarm);

        // When & Then
        mockMvc.perform(post("/api/v1/alarms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"diaryUid\": \"" + testUUID + "\", \"link\": \"" + testMusicLink.getLink() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(alarm.getId().getValue()))
                .andExpect(jsonPath("$.link").value(alarm.getLink().getLink()));
    }

}
