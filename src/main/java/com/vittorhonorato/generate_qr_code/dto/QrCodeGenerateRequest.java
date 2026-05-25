package com.vittorhonorato.generate_qr_code.dto;

import jakarta.validation.constraints.NotBlank;

public record QrCodeGenerateRequest(
        @NotBlank(message = "text é obrigatório")
        String text
) {
}
