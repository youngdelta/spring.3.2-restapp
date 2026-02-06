$m2Path = "$env:USERPROFILE\.m2\repository"
if (Test-Path $m2Path) {
    Remove-Item -Path $m2Path -Recurse -Force
    Write-Host "✓ Maven cache deleted"
} else {
    Write-Host "✓ Maven cache not found"
}
