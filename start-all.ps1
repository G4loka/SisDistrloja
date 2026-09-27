$ErrorActionPreference = "Continue"
$base = "C:\Users\gusta\Downloads\ecommerce-microservicos-utfpr"

$services = @("api-cep", "api-pagamento", "api-email", "api-fiscal", "api-entrega", "api-produtos", "loja-web")

foreach ($svc in $services) {
    $svcPath = Join-Path $base $svc
    Write-Host "Iniciando $svc em uma nova janela..."
    Start-Process "powershell" -ArgumentList "-NoExit", "-Command", "cd '$svcPath'; mvn spring-boot:run"
}

Write-Host "Todos os 7 microsserviços estão sendo iniciados em janelas separadas!"
