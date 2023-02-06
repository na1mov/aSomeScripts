package theoryNotes;

/*
Равенство объектов через Comparable<T>
*/

public class ComparableObject {
    public static void main(String[] args) {
        Beach beachOne = new Beach("nameOne", 100.0f, 5);
        Beach beachTwo = new Beach("nameTwo", 180.0f, 3);

        System.out.println(beachOne.compareTo(beachTwo));
    }

    public static class Beach implements Comparable<Beach> {
        private String name;      //название
        private float distance;   //расстояние
        private int quality;    //качество

        public Beach(String name, float distance, int quality) {
            this.name = name;
            this.distance = distance;
            this.quality = quality;
        }

        @Override
        public synchronized int compareTo(Beach beach) {
            int distance = Double.compare(this.distance, beach.getDistance());
            int quality = Integer.compare(this.quality, beach.getQuality());
            return quality - distance;
        }

        public synchronized String getName() {
            return name;
        }

        public synchronized void setName(String name) {
            this.name = name;
        }

        public synchronized float getDistance() {
            return distance;
        }

        public synchronized void setDistance(float distance) {
            this.distance = distance;
        }

        public synchronized int getQuality() {
            return quality;
        }

        public synchronized void setQuality(int quality) {
            this.quality = quality;
        }
    }
}
