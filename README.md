# Lombok @Builder constructor bug

I've run into an issue where code that's annotated with the **@Builder**
annotation doesn't compile. At the very least, the message is very confusing.
But I don't think that what's happening is actually correct.

I have an entity class that is declared like this:

```java
@Entity
@Data
@Builder
@NoArgsConstructor
public class DicomInboxImportRequest {
    @NonNull
    private String username;

    @NonNull
    private Map<String, String> parameters;

    @NonNull
    private String sessionPath;
}
```
This fails to compile with the following error message:

```
> Task :compileJava
warning: [options] bootstrap class path not set in conjunction with -source 1.7
/Users/rherrick/Development/XNAT/1.7/Issues/lombok-issue/src/main/java/org/nrg/xnat/entities/DicomInboxImportRequest.java:13: error: constructor DicomInboxImportRequest in class DicomInboxImportRequest cannot be applied to given types;
@Builder
^
  required: no arguments
  found: String,Map<String,String>,String
  reason: actual and formal argument lists differ in length
1 error
```

It *requires* a no-argument constructor but instead found one with a lot of
arguments, all of the fields declared in the class. But notice that the **@NoArgsConstructor** annotation is specified, so it has (or *should* have) a no-argument constructor. I tried removing that annotation and explicitly
declaring a no-argument constructor, with the same result. I also tried
decomposing the **@Data** annotation into **@Getter**, **@Setter**, etc., with
the same problem.

The fix, strangely enough, is to add the **@AllArgsConstructor** annotation to
the class. This is odd, because the error indicates that what's required is the
*no-argument* constructor and that it's finding the all-args constructor.

Anyway, this fixes the issue and isn't a problem, but it took me a while to
figure out and definitely looks like a bug or at least needs clarification.
