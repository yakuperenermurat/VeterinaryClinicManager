# MineSweeper

Bu Java projesi, klasik bir Mayın Tarlası oyununu simüle eder. Oyun, kullanıcının rastgele yerleştirilmiş mayınlardan kaçınarak oyun tahtasını açmaya çalıştığı bir mantık oyunudur.

## Oyun Kuralları

- Oyun tahtası belirli bir boyuta sahiptir ve kullanıcı tarafından belirlenen mayın sayısıyla doldurulur.
- Kullanıcı, tahtadaki hücreleri açar ve her açılan hücrede, hücreye komşu olan mayın sayısı veya etrafında mayın yoksa "0" değeri görüntülenir.
- Kullanıcı, bir hücreyi açtığında ve bu hücrede mayın varsa, oyun kaybedilir.
- Kullanıcı tüm mayınsız hücreleri açtığında oyun kazanılır.

## Nasıl Oynanır

1. Oyun, kullanıcı tarafından belirlenen boyutlarda bir oyun tahtası oluşturur.
2. Mayınlar rastgele yerleştirilir ve her hücrenin etrafındaki mayın sayısı hesaplanır.
3. Kullanıcı, hücreleri açarak oyun tahtasını dolaşır. Her açılan hücrede, etrafındaki mayın sayısı veya "0" değeri görüntülenir.
4. Eğer bir hücrede mayın varsa, oyun kaybedilir. Eğer tüm mayınsız hücreler açılırsa, oyun kazanılır.

## Projeyi Çalıştırma

1. Proje dosyalarını indirin veya klonlayın.
2. Proje dizinine gidin.
3. Kodu derleyin: `javac MineSweeper.java`
4. Kodu çalıştırın: `java MineSweeper`

## Oyun Kontrolleri

- Satır ve sütun numaralarını girerek hücreleri açın.
- Geçerli olmayan satır veya sütun numaraları girildiğinde veya zaten açılmış bir hücre seçildiğinde uygun hata mesajları alırsınız.
