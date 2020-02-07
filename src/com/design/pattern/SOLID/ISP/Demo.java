package com.design.pattern.SOLID.ISP;




/**
 * Interface Segregation Principle (ISP) - a recommendation about how to split interfaces into smaller interfaces
 *
 * - In this case, we need to implement the minimal signature for interface so that we don't have to deal with changing the interface
 * and whatever classes using them
 * - In case we need more implementation for them, we can eventually add more interfaces and use them in the class that will implement them, OR  create
 * another interface that will implement both the interfaces
 * - In the case of this issue, do not put more than what the client really need (in the case the class that will implement the interface)
 *
 */

class Document{

}


interface Machine{
    void print(Document doc);
    void fax(Document doc);
    void scan(Document doc);
}

class MultiFunctionPrinter implements Machine{

    @Override
    public void print(Document doc) {
        //implement something
    }

    @Override
    public void fax(Document doc) {
        //implement something
    }

    @Override
    public void scan(Document doc) {
        //implement something
    }
}

class BasicFunctionPrinter implements Machine {

    @Override
    public void print(Document doc) {
        //implement something
    }

    @Override
    public void fax(Document doc) {

        //the basic printer does not support fax, so it lead to existing function that can do nothing
        //Also we can not change the signature because if we did we have to change the interface and any other class that uses this signature
    }

    @Override
    public void scan(Document doc) {
        //the basic printer does not support fax, so it lead to existing function that can do nothing
        //Also we can not change the signature because if we did we have to change the interface and any other class that uses this signature
    }
}


//****************************
//The better alternative solution
//****************************

interface Printer{
    void print(Document doc);
}

interface Scanner {
    void scan(Document doc);
}

class BasicPrinter2 implements Printer{
    @Override
    public void print(Document doc) {
        //do some implementation here
    }
}

class MultiFunctionalPrinter implements Printer, Scanner {

    @Override
    public void print(Document doc) {
        //put some implemntation here
    }

    @Override
    public void scan(Document doc) {
        //put some implemntation here
    }
}
//YAGNI = You Aint going to need it
// You should implement the interface only if you need it.

// we can also merge the two interfaces into one
interface multiFunctionalPrinterInterface extends Printer, Scanner {

}
class MultiFunctionalPrinter2 implements multiFunctionalPrinterInterface {

    @Override
    public void print(Document doc) {

    }

    @Override
    public void scan(Document doc) {

    }
}


// Overall, make sure to put the minimal amount of code in an interface so that we can make our code make only what it suppose to do.
