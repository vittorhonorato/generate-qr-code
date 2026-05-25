## Sobre a aplicação

Esta aplicação é uma API em **Spring Boot** responsável por gerar imagens de **QR Code** a partir de um texto enviado pelo cliente e salvar o arquivo em um bucket **S3** (neste projeto, configurado para uso local via localstack`).

O fluxo foi pensado para integração com um **frontend Angular** , que envia o conteúdo do QR Code e recebe como resposta a URL final do arquivo salvo.

## Objetivo

Permitir que um cliente frontend envie um texto simples e receba uma URL pública de um QR Code em formato PNG já armazenado no S3.

## Fluxo da requisição

1. O frontend Angular envia um `POST /qrcode` com o campo `text`.
2. O backend valida o payload.
3. O serviço de QR Code gera uma imagem PNG (200x200) usando a biblioteca ZXing.
4. O arquivo é enviado para o S3 com nome único (`UUID`).
5. A API responde com a URL do arquivo salvo.

<img width="1491" height="1055" alt="Image" src="https://github.com/user-attachments/assets/75f13210-9f30-4404-9880-9413a6706cab" />

## Endpoint principal

### `POST /qrcode`

Gera um QR Code e salva no S3.

**Request body**
```json
{
  "text": "https://meusite.com"
}
```

Resposta de sucesso (200)
```json
{
  "url": "http://localhost:4566/store-qr-code/<uuid>"
}
```
