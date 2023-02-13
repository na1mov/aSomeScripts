package theoryNotes;

public class JAXBforXML {
/*    public static void main(String[] args) throws JAXBException {
        //создание объекта для сериализации в XML
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        //писать результат сериализации будем в Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //создание объекта Marshaller, который выполняет сериализацию
        JAXBContext context = JAXBContext.newInstance(Cat.class);
        // создаем «контекст». Это аналог ObjectMapper, но от него потом создаются еще два дочерних объекта Marshaller
        // – для сериализации, и Unmarshaller для десериализации. Небольшие отличия от Jackson, но – непринципиально.
        Marshaller marshaller = context.createMarshaller();
        // устанавливает свойство FORMATTED_OUTPUT в TRUE. В результат будут добавлены переносы строки и пробелы,
        // чтобы код был читабельным для человека, а не весь текст в одну строку.
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(cat, writer);

        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        System.out.println(result);
    }*/

/*    public static void deserializationOfXML() throws JAXBException {
        String xmldata = "<cat><name>Murka</name><age>5</age><weight>4</weight></cat>"";
        StringReader reader = new StringReader(xmldata);

        JAXBContext context = JAXBContext.newInstance(Cat.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Cat cat = (Cat) unmarshaller.unmarshal(reader);
    }*/
}

/*@XmlType(name = "cat")
@XmlRootElement
public class Cat {
    public String name;
    public int age;
    public int weight;

    public Cat() {
    }
}*/
