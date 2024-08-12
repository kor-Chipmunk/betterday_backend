package com.mashup.betterday.diary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.mashup.betterday.DataJpaConfig;
import com.mashup.betterday.PrivacyEncryptor;
import com.mashup.betterday.diary.model.Content;
import com.mashup.betterday.diary.model.Diary;
import com.mashup.betterday.diary.model.DiaryId;
import com.mashup.betterday.diary.model.Weather;
import com.mashup.betterday.user.model.UserId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {DataJpaConfig.class})
@DataJpaTest
class DiaryEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private PrivacyEncryptor privacyEncryptor;

    @Test
    @DisplayName("일기 내용은 암호화되어야 한다.")
    void shouldEncryptDiaryContent() throws Exception {
        final String privacyContent = "사적이고 민감한 내용이 담긴 일기입니다.";
        final String encryptContent = "암호화한 데이터입니다.";

        when(privacyEncryptor.encrypt(anyString())).thenReturn(encryptContent);
        when(privacyEncryptor.decrypt(anyString())).thenReturn(privacyContent);

        final Diary privacyDiary = Diary.write(
                new DiaryId(null, UUID.randomUUID()),
                new Content(privacyContent),
                new UserId(1L),
                Weather.SUNNY
        );

        final DiaryEntity privacyDiaryEntity = DiaryEntityConverter.toEntity(privacyDiary);

        entityManager.persist(privacyDiaryEntity);
        entityManager.flush();
        entityManager.clear();

        final EntityManager em = entityManager.getEntityManager();
        Query query = em.createNativeQuery("SELECT content From Diaries WHERE id = :id");
        query.setParameter("id", privacyDiaryEntity.getId());
        String actual = (String) query.getSingleResult();

        assertNotNull(privacyDiaryEntity);
        assertEquals(encryptContent, actual);
    }
}
