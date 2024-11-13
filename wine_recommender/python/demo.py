from gensim.models import KeyedVectors

# Path to the GloVe file
glove_path = r"C:\Users\squar\jars\glove.6B\glove.6B.50d.txt"

# Load the GloVe model using gensim
glove_model = KeyedVectors.load_word2vec_format(glove_path, binary=False, no_header=True)

# Example: Accessing the vector for a word
word = 'wine'
if word in glove_model:
    print(f"Vector for '{word}':\n{glove_model[word]}")
else:
    print(f"'{word}' not found in GloVe embeddings.")

