import java.awt.*;
import javax.swing.*;
/* 
            GROUP MEMBERS
SSEBUYIRA ANDREW JUNIOR – 26/U/0316
NAKIDAALI MARIAM –26/U/03597/PS
AKBAR SULTAN ALI –26/U/03478/PSA
AYEBALE PHILLIP – 26/U/0300
MUSASIZI SAMUEL –26/U/0375/PS
*/

// ENTITY CLASS 3: MAIN INTERFACE
public class LibraryGUI {

    // Object arrays for data management
    private static Book[] books = new Book[100];
    private static Reader[] readers = new Reader[100];

    private static int bookCount = 0;
    private static int readerCount = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("COCIS LIBRARY");

        frame.setSize(850, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Application icon
        ImageIcon appIcon = new ImageIcon("icon.jpg");
        frame.setIconImage(appIcon.getImage());

        // Colours and fonts
        Color slateNavy = new Color(34, 49, 63);
        Color lightCanvas = new Color(245, 247, 250);

        Font titleFont = new Font("SansSerif", Font.BOLD, 14);
        Font regularFont = new Font("SansSerif", Font.PLAIN, 13);

        frame.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().setBackground(lightCanvas);

        // LEFT SIDEBAR
        JPanel sidebar = new JPanel();

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(slateNavy);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        sidebar.setPreferredSize(new Dimension(320, 550));

        JLabel sectionTitle = new JLabel("DATA MANAGEMENT PANEL");

        sectionTitle.setForeground(Color.WHITE);
        sectionTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(sectionTitle);
        sidebar.add(Box.createVerticalStrut(20));

        // Book title
        JLabel tLabel = new JLabel("Book Title / Search Query:");

        tLabel.setForeground(Color.LIGHT_GRAY);
        tLabel.setFont(titleFont);
        tLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(tLabel);

        JTextField titleField = new JTextField();

        titleField.setFont(regularFont);
        titleField.setMaximumSize(new Dimension(280, 30));
        titleField.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(titleField);
        sidebar.add(Box.createVerticalStrut(10));

        // Author name
        JLabel aLabel = new JLabel("Author Name:");

        aLabel.setForeground(Color.LIGHT_GRAY);
        aLabel.setFont(titleFont);
        aLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(aLabel);

        JTextField authorField = new JTextField();

        authorField.setFont(regularFont);
        authorField.setMaximumSize(new Dimension(280, 30));
        authorField.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(authorField);
        sidebar.add(Box.createVerticalStrut(10));

        // Number of copies
        JLabel cLabel = new JLabel("Copies (For Adding New Stock):");

        cLabel.setForeground(Color.LIGHT_GRAY);
        cLabel.setFont(titleFont);
        cLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(cLabel);

        JTextField copiesField = new JTextField();

        copiesField.setFont(regularFont);
        copiesField.setMaximumSize(new Dimension(280, 30));
        copiesField.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(copiesField);
        sidebar.add(Box.createVerticalStrut(20));

        // ACTION BUTTONS
        JPanel actionGrid = new JPanel(new GridLayout(3, 2, 8, 8));

        actionGrid.setBackground(slateNavy);
        actionGrid.setMaximumSize(new Dimension(280, 110));
        actionGrid.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View All");
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");
        JButton searchButton = new JButton("Search Catal.");
        JButton regReaderButton = new JButton("Reg. Reader");

        actionGrid.add(addButton);
        actionGrid.add(viewButton);
        actionGrid.add(borrowButton);
        actionGrid.add(returnButton);
        actionGrid.add(searchButton);
        actionGrid.add(regReaderButton);

        sidebar.add(actionGrid);

        frame.add(sidebar, BorderLayout.WEST);

        // RIGHT DISPLAY AREA
        JTextArea displayArea = new JTextArea();

        displayArea.setFont(new Font("Monospaced", Font.BOLD, 13));
        displayArea.setBackground(Color.WHITE);
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(displayArea);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder("System Console Output Monitor")
        );

        frame.add(scrollPane, BorderLayout.CENTER);

        // BEHAVIOUR ENGINE

        // VIEW ALL BOOKS AND READERS
        viewButton.addActionListener(e -> updateDisplay(displayArea));

        // ADD BOOK
        addButton.addActionListener(e -> {

            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String copiesStr = copiesField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || copiesStr.isEmpty()) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Please complete Title, Author, and Copies fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (bookCount >= books.length) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Internal array register full!",
                    "Storage Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try {

                int copies = Integer.parseInt(copiesStr);

                if (copies <= 0) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "Copies must be greater than zero!"
                    );
                    return;
                }

                books[bookCount] = new Book(title, author, copies);

                bookCount++;

                displayArea.setText(
                    "\nSUCCESS: Added \"" + title + "\" to database."
                );

                titleField.setText("");
                authorField.setText("");
                copiesField.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Copies must be a numeric integer value!",
                    "Type Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // SEARCH BOOK
        searchButton.addActionListener(e -> {

            String query = titleField.getText().trim();

            if (query.isEmpty()) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Please type a Search Query into the Book Title box first!",
                    "Input Missing",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            StringBuilder results = new StringBuilder();

            results.append("================ SEARCH RESULTS ================\n\n");

            results.append(String.format(
                "%-25s | %-20s | %-15s\n",
                "BOOK TITLE",
                "AUTHOR",
                "AVAIL. COPIES"
            ));

            results.append("---------------------------------------------------------------\n");

            boolean matchesFound = false;

            for (int i = 0; i < bookCount; i++) {

                if (books[i].getTitle().equalsIgnoreCase(query)
                    || books[i].getAuthor().equalsIgnoreCase(query)) {

                    results.append(String.format(
                        "%-25s | %-20s | %-15d\n",
                        books[i].getTitle(),
                        books[i].getAuthor(),
                        books[i].getAvailableCopies()
                    ));

                    matchesFound = true;
                }
            }

            if (matchesFound) {

                displayArea.setText(results.toString());

            } else {

                displayArea.setText(
                    "\nSEARCH SYSTEM REPORT:\nNo matches found for: \""
                    + query + "\""
                );
            }
        });

        // BORROW BOOK
        borrowButton.addActionListener(e -> {

            String query = titleField.getText().trim();

            if (query.isEmpty()) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Specify Book Title to process operation!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            for (int i = 0; i < bookCount; i++) {

                if (books[i].getTitle().equalsIgnoreCase(query)) {

                    if (books[i].borrowBook()) {

                        displayArea.setText(
                            "\nRENTAL STATUS:\nSuccessfully checked out a copy of \""
                            + books[i].getTitle() + "\""
                        );

                    } else {

                        displayArea.setText(
                            "\nTRANSACTION DENIED:\nZero copies available."
                        );
                    }

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                frame,
                "Book record not found.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        });

        // RETURN BOOK
        returnButton.addActionListener(e -> {

            String query = titleField.getText().trim();

            if (query.isEmpty()) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Specify Book Title to process operation!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            for (int i = 0; i < bookCount; i++) {

                if (books[i].getTitle().equalsIgnoreCase(query)) {

                    if (books[i].returnBook()) {

                        displayArea.setText(
                            "\nRETURN TRANSACTION SUCCESSFUL:\n"
                            + "Copy logged into inventory records."
                        );

                    } else {

                        displayArea.setText(
                            "\nINCIDENT ENCOUNTERED:\n"
                            + "All registered copies are already accounted for."
                        );
                    }

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                frame,
                "Book record not found.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        });

        // REGISTER READER
        regReaderButton.addActionListener(e -> {

            if (readerCount >= readers.length) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Reader storage is full!"
                );

                return;
            }

            String readerId = JOptionPane.showInputDialog(
                frame,
                "Enter Reader ID:"
            );

            if (readerId == null || readerId.trim().isEmpty()) {
                return;
            }

            String name = JOptionPane.showInputDialog(
                frame,
                "Enter Reader Name:"
            );

            if (name == null || name.trim().isEmpty()) {
                return;
            }

            readers[readerCount] = new Reader(
                readerId.trim(),
                name.trim()
            );

            readerCount++;

            displayArea.setText(
                "\nSUCCESS: Reader registered successfully!\n"
                + "Reader ID: " + readerId.trim()
                + "\nReader Name: " + name.trim()
            );
        });

        // DISPLAY WINDOW
        frame.setVisible(true);
    }

    // METHOD TO DISPLAY ALL BOOKS AND REGISTERED READERS
    public static void updateDisplay(JTextArea displayArea) {

        StringBuilder output = new StringBuilder();

        output.append("========== LIBRARY MANAGEMENT SYSTEM ==========\n\n");

        output.append("REGISTERED BOOKS\n");
        output.append("-----------------------------------------------\n");

        if (bookCount == 0) {

            output.append("No books registered yet.\n");

        } else {

            for (int i = 0; i < bookCount; i++) {

                output.append("Title: " + books[i].getTitle() + "\n");
                output.append("Author: " + books[i].getAuthor() + "\n");
                output.append(
                    "Available Copies: "
                    + books[i].getAvailableCopies() + "\n"
                );

                output.append("-----------------------------------------------\n");
            }
        }

        output.append("\nREGISTERED READERS\n");
        output.append("-----------------------------------------------\n");

        if (readerCount == 0) {

            output.append("No readers registered yet.\n");

        } else {

            for (int i = 0; i < readerCount; i++) {

                output.append("Reader ID: " + readers[i].getReaderId() + "\n");
                output.append("Reader Name: " + readers[i].getName() + "\n");

                output.append("-----------------------------------------------\n");
            }
        }

        displayArea.setText(output.toString());
    }
}