# GUICalculator

This is a simple GUI calculator developed in Java. It utilizes [Java Swing Framework](https://en.wikipedia.org/wiki/Swing_(Java)) for the GUI, and recursive-descent parsing for calculations.

### How to input values:
The recursive-descent parsing this calculator utilizes relies on the following CFG ([Context-Free Grammar](https://en.wikipedia.org/wiki/Context-free_grammar)):

![CFG](images/cfg.png)

This is a CFG used for CSE 2231 at OSU. It is not my intellectual property.

Based on this CFG, the following expressions are valid inputs for the calculator:
```
3+4
3+(5*4)
9/(5-2)
```

And the following are invalid inputs:
```
5(4+7)
4+(3-6
-3+6
```
The first is invalid because there must be an operator preceding every parenthesis.The second is invalid because all opening parentheses must have a closing parenthesis. The last is invalid because (-) is an operator, and cannot be used to represent negative integers. According to this CFG, (-) must be preceded and followed by numbers.
