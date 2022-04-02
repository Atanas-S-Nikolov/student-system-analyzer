package student.system.analyzer.message;

public interface CommandMessages {

    String FREQUENCY_DISTRIBUTION_COMMAND = "fd";
    String MODE_COMMAND = "mode";
    String DISPERSION_COMMAND = "disp";

    String COMMANDS_DESCRIPTION = String.format("Type:\n" +
                    "'%s' - to calculate Frequency Distribution\n" +
                    "'%s' - to calculate Mode\n" +
                    "'%s' - to calculate Dispersion\n", FREQUENCY_DISTRIBUTION_COMMAND, MODE_COMMAND, DISPERSION_COMMAND);
}
