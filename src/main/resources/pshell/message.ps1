Add-Type -AssemblyName System.Windows.Forms
[System.Windows.Forms.MessageBox]::Show($args[0], $args[1], [System.Windows.Forms.MessageBoxButtons]::OK)