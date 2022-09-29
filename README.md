# gd-file-mover
Move the file to GoogleDrive

# local

```shell
gradle runFunction -Prun.functionTarget=com.tahy.gdm.GdriveMover

# run in another shell
curl --request POST \
  --url http://localhost:8080/ \
  --header 'content-type: application/json' \
  --header 'user-agent: vscode-restclient' \
  --data '{"source_file_path": "source-file-name","target_file_path": "target-file-name"}'
```

