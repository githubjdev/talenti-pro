@echo off
title Build do Maven - JDEV Academy
setlocal enabledelayedexpansion
cd /d "%~dp0"
color 0A

:: Diretório do projeto (caso queira mudar ou adicionar verificações)
set PROJECT_DIR=%CD%


:: Caminho do JAR gerado
set JAR_FILE=target\*.jar

:: Mensagens de início
echo ==================================================
echo 🔧 Iniciando o build do projeto Maven...
echo ==================================================

:: Executa o build, pulando os testes
mvn clean package -T 2C -o -DskipTests -Dmaven.test.skip=true

:: Verifica se houve erro durante a execução
IF %ERRORLEVEL% NEQ 0 (
    echo ❌ ERRO: O build falhou!
)

:: Mensagens de sucesso
echo ==================================================
echo ✅ Build finalizado com sucesso!

:: Verifica se o JAR foi gerado
if exist "%JAR_FILE%" (
    for /f %%i in ('dir /b /s %JAR_FILE%') do (
        echo -> JAR gerado em: %%i
    )
) else (
    echo ❌ ERRO: Nenhum JAR gerado.
)

echo ==================================================
:fim
endlocal
cmd /k
