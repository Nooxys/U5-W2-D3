package CiroVitiello.U5W2D3.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateTimeStamp) {
}
