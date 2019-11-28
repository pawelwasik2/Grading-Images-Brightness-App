# Description

Simple application for grading pictures brightness. Works correctly for all .jpg and .png images.
App is using HSV color model (V is for brigtness). Firstly, calculating RGB of every single pixel in photo,
then calculate V. Finally calculate average V for photo.

# allpication.conf

Example config:
```
vars {
    in="in"
	out="result"
	point="75"
}
```


First line is IN directory
Second line is OUT directroy
Third line is cut-off point, integer in range from 0 to 100

Directories names can't contain "."(dot) or ","(coma).

# Run

App can be started with a simple sbt run command or similar.
