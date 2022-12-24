package faststor;
public enum ToDoCategory
{
    EXERCISE("🚴"),
    WORK("📊"),
    RELAX("🧘"),
    TV("📺"),
    READ("📚"),
    EVENT("🎭"),
    CODE("💻"),
    COFFEE("☕"),
    EAT("🍽"),
    SHOP("🛒"),
    SLEEP("😴");

    private String emoji;

    ToDoCategory(String emoji)
    {
        this.emoji = emoji;
    }

    public String getEmoji()
    {
        return this.emoji;
    }
}
