package com.design.pattern.adaptor;

public class Challenge {
}


class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{
    Square squareAdapther;
    public SquareToRectangleAdapter(Square square)
    {
        // todo

        this.squareAdapther = square;
    }

    @Override
    public int getWidth() {
        return this.squareAdapther.side;
    }

    @Override
    public int getHeight() {
        return this.squareAdapther.side;
    }
}