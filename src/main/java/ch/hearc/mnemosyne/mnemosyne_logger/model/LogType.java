package ch.hearc.mnemosyne.mnemosyne_logger.model;

public enum LogType {
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    DEBUG("DEBUG");

    private String type;

    LogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
