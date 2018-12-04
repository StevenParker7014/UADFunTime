Write-Host "Num Args:" $args.Length;
$drive = (Get-Location).Drive.Root;

if($args[0] -ne $env:username){
	Write-Host "Other User: " $args[0];
	reg.exe LOAD HKU\Temp "$($drive)Users\$args[0]\NTUSER.DAT";
	$dir = "Registry::HKEY_USERS\Temp\Control Panel\Desktop";
}else{
	Write-Host "CURRENT USER: " $args[0];
	$dir = "Registry::HKEY_CURRENT_USER\Control Panel\Desktop";
}

if( (Test-Path $dir) ){
	Write-Host "Registry Path Found";
	Set-ItemProperty -Path $dir -Name "Wallpaper" -value $args[1];
	Set-ItemProperty -Path $dir -Name "WallpaperStyle" -value 22;
} else {
	Write-Host "Registry Path NOT Found";
}
if($args[0] -ne $env:username){
	[gc]::Collect();
	reg.exe UNLOAD HKU\Temp;
}