# metadata-detector

[![Clojars Project](https://img.shields.io/clojars/v/lt.tokenmill/metadata-detector.svg)](https://clojars.org/lt.tokenmill/metadata-detector)

Library to detect metadata from html files.

Currently detects a title and a publish date of the html.

# Usage

## Clojure

Add a dependency to your `project.clj`:

```clojure
[lt.tokenmill/metadata-detector "0.1.6" ]
```

In REPL type in:

```clojure
(require '[metadata-detector.core :refer [detect]])
; => nil
(detect "moo" (slurp "test/data/en/abcnews.html"))
; => {:title "Galaxy May Be Full of 'Second Earths'", :date "2013-02-06"}
```

## Java

As of now the JAR is stored in Clojars, therefore maven is not going to find the artifact.
You need to add the repository information to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>clojars.org</id>
        <url>http://clojars.org/repo</url>
    </repository>
</repositories>

Add a dependency to your `pom.xml`.

```xml
<dependency>
    <groupId>lt.tokenmill</groupId>
    <artifactId>metadata-detector</artifactId>
    <version>0.1.6</version>
</dependency>
```

In your Java class:

```java
package lt.tokenmill.metadatadetector;

import lt.tokenmill.metadatadetector.MetadataDetector;
import lt.tokenmill.metadatadetector.DocumentMetadata;

public static void main(String[] args) {
    MetadataDetector metadataDetector = new MetadataDetector();
    DocumentMetadata documentMetadata = metadataDetector.detect("url", new String(Files.readAllBytes(Paths.get("test/data/en/abcnews.html"))));
    String title = documentMetadata.getTitle();
    String publishDate = documentMetadata.getPublishDate();
}

```

Note that metadata-detector depends on org.clojure/clojure which must be provided.

To add clojure dependency add this snippet to your `pom.xml`:
```xml
<dependency>
    <groupId>org.clojure</groupId>
    <artifactId>clojure</artifactId>
    <version>1.8.0</version>
</dependency>
```

# TODO

- [] detect author of the document.
