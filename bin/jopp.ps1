$JAR_NAME = "jopp.jar"

$EXE_NAME = $MyInvocation.InvocationName
if ($EXE_NAME -eq "&") {
  $EXE_NAME = "& .\$($MyInvocation.MyCommand.Name -replace '\.[^\.]+$','')"
}

function Show-Usage {
    Write-Host "Usage:"
    Write-Host "  $EXE_NAME `"<your message>`""
    Write-Host "  $EXE_NAME -f <path/to/file>"
    Write-Host ""
    Write-Host "Examples:"
    Write-Host "  $EXE_NAME `"Hello, world!`""
    Write-Host "  $EXE_NAME -f notes.txt"
}

if ($args.Count -eq 0) {
    Show-Usage
    exit 1
}

# Run the JAR
java -jar $JAR_NAME @args