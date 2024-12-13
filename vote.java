import java.util.*;

// Utility class for colored text
class TextColor {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
}

// Abstract Class: User
abstract class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void login();
}

// Voter Class
class Voter extends User {
    private boolean hasVoted;
    private int age;
    public Voter(String id, String name, int age) {
        super(id, name);
        this.age = age;
        this.hasVoted = false;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void login() {
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t\tWelcome, " + getName() + "! You are now logged in as a voter." + TextColor.RESET);
    }

    public void vote(Candidate candidate, String electionName, List<String> stories) {
        if (!hasVoted) {
            candidate.incrementVotes();
            setHasVoted(true);
            stories.add("\t\t\t\t\t\tVoter " + getName() + " voted in " + electionName + ".");
            System.out.println(TextColor.GREEN + "\t\t\t\t\t\tThank you for voting, " + getName() + "!" + TextColor.RESET);
        } else {
            System.out.println(TextColor.RED + "\t\t\t\t\t\tYou have already voted." + TextColor.RESET);
        }
    }
}

// Admin Class
class Admin extends User {
    private String password;

    public Admin(String id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    @Override
    public void login() {
        System.out.println(TextColor.BLUE + "\t\t\t\t\t\t================================================================" + TextColor.RESET);
        System.out.println(TextColor.YELLOW + "\n\n\t\t\t\t\t\t\tWelcome, Admin " + getName() + "! You are now logged in.\n\n" + TextColor.RESET);
        System.out.println(TextColor.BLUE + "\t\t\t\t\t\t================================================================" + TextColor.RESET);
    }

    public void addElection(Map<String, Map<Integer, Candidate>> elections, List<String> stories, Scanner scanner) {
        System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter the election name: " + TextColor.RESET);
        String electionName = scanner.nextLine();
        
        if (elections.containsKey(electionName)) {
            System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tElection already exists." + TextColor.RESET);
            return;
        }
    
        Map<Integer, Candidate> candidates = new HashMap<>();
        System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter the number of candidates: " + TextColor.RESET);
        int numCandidates = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        for (int i = 0; i < numCandidates; i++) {
            System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter Candidate Name " + (i + 1) + ": " + TextColor.RESET);
            String candidateName = scanner.nextLine();
            candidates.put(candidates.size() + 1, new Candidate(candidates.size() + 1, candidateName));
        }
    
        elections.put(electionName, candidates);
        stories.add("\t\t\t\t\t\t\t\t\tAdmin added a new election: " + electionName + ".");
        System.out.println(TextColor.GREEN + "\n\t\t\t\t\t\t\t\t\tElection \"" + electionName + "\" created successfully.\n" + TextColor.RESET);
    }


    public void displayResults(Map<String, Map<Integer, Candidate>> elections) {
        System.out.println(TextColor.BLUE + "\n\t\t\t\t\t\t\t================= Election Results =================\n" + TextColor.RESET);
        if (elections.isEmpty()) {
            System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tElection list is empty." + TextColor.RESET);
        } else {
            for (Map.Entry<String, Map<Integer, Candidate>> election : elections.entrySet()) {
                System.out.println(TextColor.YELLOW + "\n\t\t\t\t\t\t\t\tElection: " + election.getKey() + TextColor.RESET);
                Map<Integer, Candidate> candidates = election.getValue();

                if (candidates.isEmpty()) {
                    System.out.println(TextColor.RED + "\t\t\t\t\t\t\tNo candidates in this election." + TextColor.RESET);
                } else {
                    for (Candidate candidate : candidates.values()) {
                        System.out.println(TextColor.PURPLE + "\t\t\t\t\t\t\t\t" + candidate.getName() + ": \t\t\t\t" + candidate.getVotes() + " votes" + TextColor.RESET);
                    }
                }
            }
        }
    }
    public void viewStories(List<String> stories) {
        System.out.println(TextColor.BLUE + "\n\t\t\t\t\t\t\t================ Election Stories =====================" + TextColor.RESET);
        if (stories.isEmpty()) {
            System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\t\t\tNo stories to display.\n" + TextColor.RESET);
        } else {
            for (String story : stories) {
                System.out.println(TextColor.CYAN + story + TextColor.RESET);
            }
        }
    }
}

// Candidate Class
class Candidate {
    private int id;
    private String name;
    private int votes;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.votes = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void incrementVotes() {
        this.votes++;
    }
}

// Main Program`
public class vote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("A001", "Alejandra", "ale123");
        Map<String, Voter> voters = new HashMap<>();
        List<String> stories = new ArrayList<>();
        Map<Integer, Candidate> candidates = new HashMap<>();
        String electionName = "Default Election";
        Map<String, Map<Integer, Candidate>> elections = new HashMap<>();

        System.out.println();
        System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==                                                           ==" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==          V   V      OOO      TTTTT     EEEEE              ==" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==          V   V     O   O       T       E                  ==" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==          V   V     O   O       T       EEEE               ==" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==           V V      O   O       T       E                  ==" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==            V        OOO        T       EEEEE              ==" + TextColor.RESET);
        System.out.println(TextColor.CYAN + "\t\t\t\t\t\t==                                                           ==" + TextColor.RESET);
        System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
        while (true) {

            System.out.println();
            System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
            System.out.println(TextColor.CYAN + "\t\t\t\t\t\t\t\t        1. Sign Up (Voter)");
            System.out.println("\t\t\t\t\t\t\t\t        2. Sign In (Voter)");
            System.out.println("\t\t\t\t\t\t\t\t        3. Admin Login");
            System.out.println("\t\t\t\t\t\t\t\t        4. Exit" + TextColor.RESET);
            System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
            System.out.println();
            System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t          Enter your choice: " + TextColor.RESET);
            // Error Handling
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\t\t\tInvalid input. Please enter a valid number" + TextColor.RESET);
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    clearScreen();
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t============================================================" + TextColor.RESET);
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t--                         Sign Up                        --" + TextColor.RESET);
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t============================================================" + TextColor.RESET);
                    // Voter ID validationn for error handling
                    String voterId;
                    while (true) {
                        System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter Voter ID: " + TextColor.RESET);
                        voterId = scanner.nextLine();
                        if (voters.containsKey(voterId)) {
                            System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\tVoter ID already exists. Please try again with a different ID." + TextColor.RESET);
                        } else if (voterId.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\tInvalid Voter ID. Please enter numeric values only.\n" + TextColor.RESET);
                        }
                    }
                    System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter Name: " + TextColor.RESET);
                    String voterName = scanner.nextLine();
                    // Age validation for error handling
                    int voterAge;
                    while (true) {
                        System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter Age: " + TextColor.RESET);
                        String ageInput = scanner.nextLine();
                        try {
                            voterAge = Integer.parseInt(ageInput);
                            if (voterAge < 18) {
                                System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\tYou must be at least 18 years old to register." + TextColor.RESET);
                                continue; 
                            }
                            break; // Valid age entered, exit loop
                        } catch (NumberFormatException e) {
                            System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\t\tInvalid age. Please enter numeric values only.\n" + TextColor.RESET);
                        }
                    }
                    // Successful registration
                    voters.put(voterId, new Voter(voterId, voterName, voterAge));
                    System.out.println(TextColor.GREEN + "\n\t\t\t\t\t\t\t\t    Voter registered successfully!" + TextColor.RESET);
                    break;
                }
                case 2 -> {
                    clearScreen();
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t\t\t          Sign In          " + TextColor.RESET);
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
                    System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter Voter ID: " + TextColor.RESET); 
                    String voterId = scanner.nextLine();
                    Voter voter = voters.get(voterId);
                
                    if (voter == null) {
                        System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tVoter not found." + TextColor.RESET);
                        continue;
                    }
                
                    voter.login();
                
                    // Display available elections
                    if (elections.isEmpty()) {
                        System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tNo elections available." + TextColor.RESET);
                        continue;
                    }
                
                    System.out.println(TextColor.BLUE + "\n\t\t\t\t\t\t======================== Elections ===========================" + TextColor.RESET);
                    List<String> electionList = new ArrayList<>(elections.keySet());
                    for (int i = 0; i < electionList.size(); i++) {
                        System.out.println(TextColor.PURPLE + "\t\t\t\t\t\t\t\t\t" + (i + 1) + ". " + electionList.get(i) + TextColor.RESET);
                    }
                
                    // Prompt voter to select an election
                    int electionChoice;
                    while (true) {
                        System.out.print(TextColor.YELLOW + "\n\t\t\t\t\t\t\t\tSelect Election (Enter number): " + TextColor.RESET);
                        String choiceInput = scanner.nextLine();
                        try {
                            electionChoice = Integer.parseInt(choiceInput);
                            if (electionChoice >= 1 && electionChoice <= electionList.size()) {
                                break;
                            } else {
                                System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\tInvalid choice. Please select a valid election." + TextColor.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\t\tInvalid input. Please enter a number." + TextColor.RESET);
                        }
                    }
                
                    // Load selected election's candidates
                    electionName = electionList.get(electionChoice - 1);
                    candidates = elections.get(electionName);
                
                    System.out.println(TextColor.BLUE + "\n\t\t\t\t\t\t======================== Candidates ===========================" + TextColor.RESET);
                    if (candidates.isEmpty()) {
                        System.out.println(TextColor.PURPLE + "\n\t\t\t\t\t\t\t\t\tNo candidates available in this election." + TextColor.RESET);
                        continue;
                    } else {
                        for (Candidate candidate : candidates.values()) {
                            System.out.println(TextColor.PURPLE + "\t\t\t\t\t\t\t\t\t" + candidate.getId() + ". " + candidate.getName() + TextColor.RESET);
                        }
                    }
                
                    // Candidate ID input and validation
                    int candidateId;
                    while (true) {
                        System.out.print(TextColor.YELLOW + "\n\t\t\t\t\t\t\t\tEnter Candidate ID to vote: " + TextColor.RESET);
                        String candidateIdInput = scanner.nextLine();
                        try {
                            candidateId = Integer.parseInt(candidateIdInput);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(TextColor.RED + "\n\t\t\t\t\t\t\t\tInvalid input. Please enter a numeric candidate ID." + TextColor.RESET);
                        }
                    }
                
                    // Validate if the candidate ID exists
                    Candidate candidate = candidates.get(candidateId);
                    if (candidate != null) {
                        voter.vote(candidate, electionName, stories);
                        System.out.println(TextColor.GREEN + "\t\t\t\t\t\t\t\t\tVote cast successfully!" + TextColor.RESET);
                    } else {
                        System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tInvalid candidate ID. Vote not cast." + TextColor.RESET);
                    }
                    break; // Exit the sign-in process after voting
                }
                    
                case 3 -> {
                    clearScreen();
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t===============================================================" + TextColor.RESET);
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t==                       Admin Password                      ==" + TextColor.RESET);
                    System.out.println(TextColor.BLUE + "\t\t\t\t\t\t================================================================" + TextColor.RESET);
                    System.out.print(TextColor.YELLOW + "\t\t\t\t\t\t\t\t\tEnter Admin Password: " + TextColor.RESET);
                    String password = scanner.nextLine();

                    if (admin.authenticate(password)) {
                        clearScreen();
                        admin.login();
                        boolean adminLoggedIn = true;
                        while (adminLoggedIn) {
                            try {
                                System.out.println(TextColor.BLUE + "\n\t\t\t\t\t\t=================================================================" + TextColor.RESET);
                                System.out.println(TextColor.BLUE + "\t\t\t\t\t\t==                       Admin Menu                            ==" + TextColor.RESET);
                                System.out.println(TextColor.BLUE + "\t\t\t\t\t\t=================================================================" + TextColor.RESET);
                                System.out.println(TextColor.CYAN + "\t\t\t\t\t\t\t\t\t1. Add Election");
                                System.out.println("\t\t\t\t\t\t\t\t\t2. View Results");
                                System.out.println("\t\t\t\t\t\t\t\t\t3. View Stories");
                                System.out.println("\t\t\t\t\t\t\t\t\t4. Logout" + TextColor.RESET);
                                System.out.print(TextColor.YELLOW + "\n\t\t\t\t\t\t\t\t\tEnter your choice: " + TextColor.RESET);
                        
                                if (!scanner.hasNextInt()) {
                                    System.out.println(TextColor.RED + "\t\t\t\t\t\t\tInvalid input. Please enter a number between 1 and 4." + TextColor.RESET);
                                    scanner.nextLine();
                                    continue;
                                }
                        
                                int adminChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                        
                                switch (adminChoice) {
                                    case 1 -> admin.addElection(elections, stories, scanner);
                                    case 2 -> admin.displayResults(elections);
                                    case 3 -> admin.viewStories(stories);
                                    case 4 -> {
                                        System.out.println(TextColor.GREEN + "\t\t\t\t\t\t\t\t\tAdmin logged out." + TextColor.RESET);
                                        adminLoggedIn = false;
                                    }
                                    default -> System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\tInvalid choice. Please select between 1 and 4." + TextColor.RESET);
                                }
                            } catch (Exception e) {
                                System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tAn error occurred: " + e.getMessage() + TextColor.RESET);
                                scanner.nextLine();
                            }
                        }
                    } else {
                        System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tInvalid password." + TextColor.RESET);
                    }
                }
                case 4 -> {
                    System.out.println(TextColor.GREEN + "\t\t\t\t\t\t\t\t\tExiting the system..." + TextColor.RESET);
                    System.exit(0);
                }
                default -> System.out.println(TextColor.RED + "\t\t\t\t\t\t\t\t\tInvalid choice." + TextColor.RESET);
            }
        }
    }
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}