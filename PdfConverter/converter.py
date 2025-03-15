import fitz
import os


# Converte arquivos PDF em texto
def convert_pdf_to_text(pdf_folder, output_folder):
    if not os.path.exists(
            output_folder):  # Verifica se a pasta de saída existe.
        os.makedirs(
            output_folder)  # Cria a pasta de saída caso ela não exista.

    for pdf_file in os.listdir(
            pdf_folder):  # Itera sobre os arquivos da pasta dos PDFs.
        if pdf_file.endswith(
                '.pdf'):  # Processa apenas arquivos com extensão '.pdf'.
            pdf_path = os.path.join(
                pdf_folder, pdf_file)  # Caminho completo do arquivo PDF.
            output_path = os.path.join(
                output_folder, pdf_file.replace('.pdf', '.txt')
            )  # Define o caminho completo do arquivo de texto de saída, trocando a extensão.

            try:
                with fitz.open(pdf_path) as doc:
                    text = ""
                    for page in doc:  # Itera sobre todas as páginas do PDF.
                        text += page.get_text(
                        )  # Extrai o texto de cada página e adiciona à string.

                    with open(
                            output_path, 'w', encoding='utf-8'
                    ) as f:  # Abre o arquivo de saída no modo de escrita com codificação UTF-8.
                        f.write(
                            text
                        )  # Escreve o texto extraído no arquivo de saída.
                print(f"Convertido: {pdf_file}")
            except Exception as e:
                print(f"Erro ao processar {pdf_file}: {e}")


if __name__ == "__main__":
    # Solicita ao usuário o caminho da pasta contendo os PDFs
    pdf_folder = input(
        "Digite o caminho da pasta contendo os arquivos PDF: ").strip()

    # Verifica se o caminho informado existe
    if not os.path.exists(pdf_folder):
        print(
            "A pasta especificada não existe. Verifique o caminho e tente novamente."
        )
    else:
        # Define a pasta de saída
        output_folder = "TextosConvertidos"

        # Chama a função de conversão
        convert_pdf_to_text(pdf_folder, output_folder)
