param (
    [switch]$Force
)

$sourceRelativePath = "config\games\debug"
$sourceDirectory = Join-Path (Get-Location) $sourceRelativePath

# Check if the config directory exists
if (-not (Test-Path $sourceDirectory)) {
    Write-Host "Error: The ./config directory does not exist."
    exit
}


$destinationDirectories = Get-ChildItem -Path "./games" -Directory | Where-Object { $_.Name -ne "debug" }
foreach ($destDir in $destinationDirectories) {
    $filesInDestination = Get-ChildItem -Path $destDir.FullName

    # Check if the destination directory has exactly one file
    if ($Force -or ($filesInDestination.Count -eq 1 -and $filesInDestination[0] -is [System.IO.FileInfo] -and $filesInDestination[0].Name -eq ".gitkeep")) {
        $destinationPath = $destDir.FullName
        Copy-Item -Path "$sourceDirectory\*" -Destination $destinationPath -Force -Recurse
        Write-Host "Game debug copied to $destinationPath"
    }
}
