# 🔗 Hybrid Search — Fuzzy + Embedding 

## 🌿 1. Overview

This project implements **Hybrid Search**, combining:

### 🔍 Fuzzy Search

Handles spelling mistakes and near-matches.

### 🧠 Embedding Search

Understands meaning and returns semantically similar results.

Together they produce **much more accurate results**.

---

## 🤝 Why Hybrid?

### ❌ Only Fuzzy Search

Good when typos happen — but it only checks spelling.

### ❌ Only Embedding Search

Understands meaning — but may return too many results.

### ✅ Hybrid Search (Best of both worlds)

Process:

```
1️⃣ Fuzzy filters relevant candidates
2️⃣ Embedding ranks them by meaning
```

So the system returns:

✔ Relevant<br>
✔ Correctly ranked<br>
✔ Noise reduced<br>

---

## 🤔 Example

### Query

```
redis
```

### Result

```
1. Redis cache improves application performance
2. In-memory caching using Redis
```

Instead of showing unrelated results, Hybrid ensures Redis-focused answers appear first.

---

## 🏗 2. Architecture

```
User
   ↓
Hybrid API
   ↓
Fuzzy Filter  → removes irrelevant records
   ↓
Embedding Scoring → calculates similarity
   ↓
Final Ranked Results
```

---

## 🔎 3. How Hybrid Works (Step-By-Step)

1️⃣ User sends query<br>
2️⃣ Perform fuzzy search to shortlist candidates<br>
3️⃣ Generate embedding for query<br>
4️⃣ Compare with stored embeddings<br>
5️⃣ Sort based on similarity score<br>
6️⃣ Return most meaningful results<br>

---

## 🌐 4. API Endpoint

### Request

```
GET /api/hybrid?query=<text>&fuzzy=<distance>&semantic=<score>
```

### Example

```
http://localhost:8080/api/hybrid?query=redis&fuzzy=1&semantic=0.8
```

### Response

```json
[
  {
    "text": "Redis cache improves application performance",
    "score": 1.0
  },
  {
    "text": "In-memory caching using Redis",
    "score": 0.99
  }
]
```

---

## ⚙ Parameters Explained

| Parameter  | Meaning                      |
| ---------- | ---------------------------- |
| `fuzzy`    | Allowed spelling distance    |
| `semantic` | Minimum similarity threshold |

This makes search fully configurable.

---

## 🛠 5. Technologies Used

* Java
* Spring Boot
* MongoDB
* Fuzzy Logic (Levenshtein)
* Embedding Similarity
* Maven

---

## ▶️ 6. How to Run

### 1️⃣ Start MongoDB

Ensure Mongo service is running.

### 2️⃣ Run application

```
mvn spring-boot:run
```

### 3️⃣ Test API

```
http://localhost:8080/api/hybrid?query=redis&fuzzy=2&semantic=0.7
```

---

## 📚 7. What I Learned

✔ Combining search strategies<br>
✔ Ranking by similarity<br>
✔ Reducing noise in results<br>
✔ Designing configurable APIs<br>

---

## 🚀 8. Future Enhancements

* Weighted scoring (more weight to fuzzy or embedding)
* UI search box
* Analytics dashboard

---

## ✅ Summary

> Hybrid search intelligently combines fuzzy matching and semantic similarity, delivering accurate, flexible, and meaningful search results.


