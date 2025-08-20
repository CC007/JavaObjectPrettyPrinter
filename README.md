# Java Object Pretty Printer (JOPP)

Pretty prints a Java-style toString() output like: Person[name=Alice, address=Address[street=Main, city=NY]]
Handles:
- Nested brackets
- Skips formatting inside string literals
- Works with records
- Works with Lombok's default format: User(username=bob, id=42)
- Works with JSON format as well
- Works with a mix of the above