package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks // @Mock 객체 들로 DI 해준다.
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        // 아래의 코드는 위의 어노테이션 들을 이용하여 주입 받는 것과 같은 결과이다.
//        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);


//        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
//            .thenReturn(true);
        // @Spy의 경우 실제 객체를 사용하므로 stubbing 과는 다른 메서드를 이용한다.
        // @Spy 를 이용했기에 mailSendClient 부분은 mock 객체를 사용하고 나머지는 실제 객체의 메서드를 사용하게 된다.
        doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());

        // BDDMockito를 이용하면 아래와 같이 BDD (given-when-then) 패턴에 적합하게 작성할 수 있다.
        // BDDMockito는 Mockito를 상속받아서 한단계 추상화했을 뿐이다.
        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }

}