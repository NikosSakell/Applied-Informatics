import pandas as pd
import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

def ib2(input_file, output_file, k=3, threshold=0.9):
    # Read the normalized CSV file using pandas
    df = pd.read_csv(input_file)

    # Separate features and class labels
    X = df.iloc[:, :-1].values
    y = df.iloc[:, -1].values

    # Split the dataset into training and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

    # Train a k-NN classifier on the training set
    knn_classifier = KNeighborsClassifier(n_neighbors=k)
    knn_classifier.fit(X_train, y_train)

    # Make predictions on the test set
    y_pred = knn_classifier.predict(X_test)

    # Calculate accuracy on the test set
    accuracy = accuracy_score(y_test, y_pred)

    # Identify instances misclassified by their nearest neighbors
    misclassified_indices = np.where(y_test != y_pred)[0]

    # Keep instances with classification confidence below the threshold
    selected_indices = []
    for idx in misclassified_indices:
        neighbors_indices = knn_classifier.kneighbors([X_test[idx]], return_distance=False)[0]
        confidence = np.sum(y_train[neighbors_indices] == y_test[idx]) / k
        if confidence < threshold:
            selected_indices.append(idx)

    # Create a new DataFrame with the selected examples
    df_ib2 = df.iloc[selected_indices]

    # Write the reduced data to a new CSV file
    df_ib2.to_csv(output_file, index=False)

if __name__ == "__main__":
    input_file = r"C:\Users\HP\Desktop\Computer Science\7ο Εξάμηνο\Ανακάλυψη γνώσης από Βάσεις Δεδομένων\pp1\pp1\NormalizeValuesOutput\letter-recognition-normalized-output.csv"  # Replace with the path to your normalized input CSV file
    output_file = r"C:\Users\HP\Desktop\Computer Science\7ο Εξάμηνο\Ανακάλυψη γνώσης από Βάσεις Δεδομένων\pp1\pp1\letter-recognition-ib2-output.csv"  # Replace with the desired output path

    ib2(input_file, output_file)
