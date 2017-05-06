package groovy.ru.egartech.domain

class Storage {

    String uniqName                        // Уникальное имя склада
    int capacity                           // Объем склада
    List<ProductCategoryEnum> catList = [] // Список категорий товаров, которые может принимать склад

    // Текущее состояние склада
    int freeVolume                     // Количество свободных мест
    List<Product> currentProducts = [] // Товары, хранящиеся на складе

    Storage(String uniqName, int capacity, List<ProductCategoryEnum> catList) {
        this.uniqName = uniqName
        this.capacity = capacity
        this.catList = catList
        freeVolume = capacity // В момент создания склад пуст
    }

    // При добалении товара на склад
    void onAddingProduct(Product product) {
        freeVolume -= product.occupiedVolume
        currentProducts << product
    }

}