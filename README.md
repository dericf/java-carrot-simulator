# Carrot Simulator

This project is meant to help teach students how to use `processing` in a full Java environment, as opposed to using the processing IDE.

The objective is not to make a realistic carrot simulator, but rather to show how to organize/compose a processing app using `classes`, `abstract classes`, `ArrayList`, and more.

The most important point is that every component that will be drawn, should extend the `DrawableComponent` abstract class which **requires at least the following two methods to be implemented**.


```Java
/* essentially pre-draw (called as first line of draw)*/
public abstract void calculate();

/* Draws the component to the window */
public abstract void draw();
```