
/******************************************************************
 * COMP308 Java for Programmer,
 * SCIS, Athabasca University
 *
 * Assignment: TME2
 * 
 * @author: Steve Leung
 * @date : Oct 21, 2005
 *
 ******************************************************************/

abstract class Product {
    protected float price;

    // return the price of a particular product
    abstract float price();
}

// ------------------------------------------------------------

// These three iterfaces are added to distinguish the proper order for the
// product
interface IComputerOrder {
}

interface IPartyTrayOrder {
}

interface IComputerPartyOrder {
}

class ComputerPart extends Product implements IComputerOrder, IComputerPartyOrder {
    public ComputerPart(float p) {
        price = p;
    }

    public float price() {
        return price;
    }
}

class Motherboard extends ComputerPart {
    protected String manufacturer;

    public Motherboard(String mfg, float p) {
        super(p);
        manufacturer = mfg;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}

class RAM extends ComputerPart {
    protected int size;
    protected String manufacturer;

    public RAM(String mfg, int size, float p) {
        super(p);
        this.manufacturer = mfg;
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}

class Drive extends ComputerPart {
    protected String type;
    protected int speed;

    public Drive(String type, int speed, float p) {
        super(p);
        this.type = type;
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }
}

class Peripheral extends Product implements IComputerOrder, IComputerPartyOrder {
    public Peripheral(float p) {
        price = p;
    }

    public float price() {
        return price;
    }
}

class Printer extends Peripheral {
    protected String model;

    public Printer(String model, float p) {
        super(p);
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

class Monitor extends Peripheral {
    protected String model;

    public Monitor(String model, float p) {
        super(p);
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

class Service extends Product implements IComputerOrder, IPartyTrayOrder, IComputerPartyOrder {
    public Service(float p) {
        price = p;
    }

    public float price() {
        return price;
    }
}

class AssemblyService extends Service {
    String provider;

    public AssemblyService(String pv, float p) {
        super(p);
        provider = pv;
    }

    public String getProvider() {
        return provider;
    }
}

class DeliveryService extends Service {
    String courier;

    public DeliveryService(String c, float p) {
        super(p);
        courier = c;
    }

    public String getCourier() {
        return courier;
    }
}

// -------------------------------------------------------
class Cheese extends Product implements IPartyTrayOrder, IComputerPartyOrder {
    public Cheese(float p) {
        price = p;
    }

    public float price() {
        return price;
    }
}

class Cheddar extends Cheese {
    public Cheddar(float p) {
        super(p);
    }
}

class Mozzarella extends Cheese {
    public Mozzarella(float p) {
        super(p);
    }
}

class Fruit extends Product implements IPartyTrayOrder, IComputerPartyOrder {
    public Fruit(float p) {
        price = p;
    }

    public float price() {
        return price;
    }
}

class Apple extends Fruit {
    public Apple(float p) {
        super(p);
    }
}

class Orange extends Fruit {
    public Orange(float p) {
        super(p);
    }
}