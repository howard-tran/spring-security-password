package com;

public interface parent {
  void hello();
}

public class A implements parent {

  A() {}

  @Override
  public void hello() {
    System.out.print("A");
  }
}

public class B implements parent {

  B() {}

  @Override
  public void hello() {
    System.out.print("B");
  }
}

public class App {
  A dev;

  App() {
    dev = new A();
  }

  void mainFunc() {
    dev.hello();
  }
}

public class Main {

  public static void main(String[] args) {
    App app = new App();
    app.mainFunc();
  }
}
