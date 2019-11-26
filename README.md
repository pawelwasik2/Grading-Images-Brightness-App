# Description

Simple application for grading pictures brightness. Works correctly for all demo images, cut-off point is default 75 but it works correctly for cut-off point from 63 to 84 also. However, app works for any images.

# Config.txt

Example config:
> too_dark <br />
> result <br />
> 75

First line is IN directory
Second line is OUT directroy
Third line is cut-off point, integer in range from 0 to 100

Directories names can't contain "."(dot) or ","(coma).

# Run

App can be started with a simple sbt run command or similar.
