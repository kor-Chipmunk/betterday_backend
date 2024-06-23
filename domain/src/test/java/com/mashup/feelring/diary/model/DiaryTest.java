package com.mashup.feelring.diary.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import com.mashup.feelring.diary.exception.DiaryValidationException;
import com.mashup.feelring.user.model.UserId;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class DiaryTest {

    @DisplayName("작성")
    @Nested
    class WriteUsecase {
        @DisplayName("일기를 작성합니다.")
        @Test
        void shouldWrite() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                DiaryId diaryId = new DiaryId(0L, UUID.randomUUID());
                Diary writtenDiary = Diary.write(
                        diaryId,
                        "내용",
                        new UserId(1L),
                        Weather.SUNNY
                );

                assertAll(
                        "writtenDiary",
                        () -> assertEquals("내용", writtenDiary.getContent()),
                        () -> assertEquals(diaryId, writtenDiary.getId()),
                        () -> assertEquals(Weather.SUNNY, writtenDiary.getWeather()),
                        () -> assertEquals(new UserId(1L), writtenDiary.getUserId()),
                        () -> assertEquals(mockNow, writtenDiary.getCreatedAt()),
                        () -> assertEquals(mockNow, writtenDiary.getUpdatedAt())
                );
            }
        }

        @DisplayName("내용은 최대 길이를 넘으면 예외(DiaryValidationException)를 일으킵니다")
        @Test
        void shouldRaiseExceptionWhenLengthIsOverMax() {
            DiaryId diaryId = new DiaryId(0L, UUID.randomUUID());
            assertThrows(DiaryValidationException.class, () -> {
                Diary.write(
                        diaryId,
                        "가".repeat(Diary.MAX_CONTENT_LENGTH + 1),
                        new UserId(1L),
                        Weather.SUNNY
                );
            });
        }
    }

    @DisplayName("수정")
    @Nested
    class EditUsecase {
        @DisplayName("일기를 수정합니다.")
        @Test
        void shouldEdit() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            DiaryId diaryId = new DiaryId(0L, UUID.randomUUID());
            Diary editedDiary = Diary.write(
                    diaryId,
                    "내용",
                    new UserId(1L),
                    Weather.SUNNY
            );

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                editedDiary.edit(
                        "수정한 내용",
                        Weather.CLOUDY
                );

                assertAll(
                        "editedDiary",
                        () -> assertEquals("수정한 내용", editedDiary.getContent()),
                        () -> assertEquals(Weather.CLOUDY, editedDiary.getWeather()),
                        () -> assertEquals(mockNow, editedDiary.getUpdatedAt())
                );
            }
        }

        @DisplayName("내용은 최대 길이를 넘으면 예외(DiaryValidationException)를 일으킵니다")
        @Test
        void shouldRaiseExceptionWhenLengthIsOverMax() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            DiaryId diaryId = new DiaryId(0L, UUID.randomUUID());
            Diary editedDiary = Diary.write(
                    diaryId,
                    "내용",
                    new UserId(1L),
                    Weather.SUNNY
            );

            assertThrows(DiaryValidationException.class, () -> {
                editedDiary.edit(
                        "가".repeat(Diary.MAX_CONTENT_LENGTH + 1),
                        Weather.SUNNY
                );
            });
        }
    }

    @DisplayName("삭제")
    @Nested
    class DeleteUsecase {
        @DisplayName("일기를 삭제합니다.")
        @Test
        void shouldDelete() {
            LocalDateTime mockNow = LocalDateTime.of(2024,5,27,0,0,0);

            DiaryId diaryId = new DiaryId(0L, UUID.randomUUID());
            Diary deletedDiary = Diary.write(
                    diaryId,
                    "내용",
                    new UserId(1L),
                    Weather.SUNNY
            );

            try (MockedStatic<LocalDateTime> mock = mockStatic(LocalDateTime.class)) {
                mock.when(LocalDateTime::now).thenReturn(mockNow);

                deletedDiary.delete();

                assertAll(
                        "deletedDiary",
                        () -> assertEquals(mockNow, deletedDiary.getDeletedAt()),
                        () -> assertEquals(mockNow, deletedDiary.getUpdatedAt())
                );
            }
        }
    }

}
