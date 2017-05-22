/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uwmc.panzer;

/**
 * @author Patrick Wiehle
 */
public abstract class GameObject {

    private Coordinate objectPosition;
    private double width;
    private double height;
    private double movingAngle;
    private double movingDistance;

    public GameObject(Coordinate objectPosition, double width, double height) {
        this.objectPosition = objectPosition;
        this.width = width;
        this.height = height;
        movingAngle = 0;
        movingDistance = 0;
    }

    public Coordinate getObjectPosition() {
        return objectPosition;
    }

    public void setObjectPosition(Coordinate objectPosition) {
        this.objectPosition = objectPosition;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMovingAngle() {
        return movingAngle;
    }

    public void setMovingAngle(double movingAngle) {
        this.movingAngle = movingAngle;
    }

    public double getMovingDistance() {
        return movingDistance;
    }

    public void setMovingDistance(double movingDistance) {
        this.movingDistance = movingDistance;
    }

    public boolean isLeftOf(GameObject that) {
        return this.getObjectPosition().getX() + this.getWidth() < that.getObjectPosition().getX();
    }

    public boolean isAbove(GameObject that) {
        return this.getObjectPosition().getY() + this.getHeight() < that.getObjectPosition().getY();
    }

    public boolean touches(GameObject that) {
        if (this.isLeftOf(that)) return false;
        if (that.isLeftOf(this)) return false;
        if (this.isAbove(that)) return false;
        if (that.isAbove(this)) return false;

        return true;
    }

    public static Coordinate polarToCartesianCoordinates(double angle) {

        // X-Achse zeigt nach Osten, Y-Achse zeigt nach Süden beim Zeichnen
        double x = Math.cos(angle);
        double y = Math.sin(angle);

        return new Coordinate(x, y);
    }

    public void moveGameObject() {

        Coordinate direction = polarToCartesianCoordinates(movingAngle);
        double newX = objectPosition.getX() + direction.getX() * movingDistance;
        double newY = objectPosition.getY() + direction.getY() * movingDistance;
        //if (!((newX < 0) || newX > 1180 - width))
        objectPosition.setX(newX);
        //if (!((newY < 0) || newY > 780 - height))
        objectPosition.setY(newY);
    }

    public void makeMove() {
        moveGameObject();
    }

    public void makeMoveFriendly() {
        Coordinate direction = polarToCartesianCoordinates(movingAngle);
        double newX = objectPosition.getX() + direction.getX() * movingDistance;
        double newY = objectPosition.getY() + direction.getY() * movingDistance;
        if (!((newX < 0) || newX > 1180 - width))
            objectPosition.setX(newX);
        if (!((newY < 0) || newY > 780 - height))
            objectPosition.setY(newY);
    }

    protected abstract void paintMe(java.awt.Graphics g);

}
