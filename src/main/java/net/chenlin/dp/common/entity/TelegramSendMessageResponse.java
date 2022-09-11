package net.chenlin.dp.common.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TelegramSendMessageResponse {
    private boolean ok;
    private TelegramResult result;

    @Data
    class TelegramResult {
        private Integer message_id;
        private TelegramFrom from;
        private TelegramChat chat;
        private Long date;
        private String text;

        @Data
        class TelegramFrom {
            private String id;
            private boolean is_bot;
            private String first_name;
            private String username;
        }

        @Data
        class  TelegramChat {
            private String id;
            private String title;
            private String type;

        }
    }


}
