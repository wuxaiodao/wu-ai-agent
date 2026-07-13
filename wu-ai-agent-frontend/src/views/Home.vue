<template>
  <div class="home-container cyber-bg">
    <!-- Animated Background Lines -->
    <div class="bg-lines"></div>
    
    <!-- Header -->
    <header class="hero-section">
      <h1 class="main-title glitch" data-text="WU AI AGENT">
        <span class="title-wu">WU</span>
        <span class="title-ai">AI</span>
        <span class="title-agent">AGENT</span>
      </h1>
      <p class="subtitle">// 智能驱动 · 无限可能 //</p>
      <div class="divider">
        <span class="divider-dot"></span>
        <span class="divider-line"></span>
        <span class="divider-dot"></span>
      </div>
    </header>

    <!-- App Cards -->
    <div class="app-list">
      <!-- Love Master -->
      <div class="app-card love-card" @click="goToLoveApp">
        <div class="card-glow"></div>
        <div class="card-content">
          <div class="card-icon">
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" fill="url(#love-gradient)"/>
              <defs>
                <linearGradient id="love-gradient" x1="12" y1="3" x2="12" y2="21">
                  <stop offset="0%" style="stop-color:#ff006e"/>
                  <stop offset="100%" style="stop-color:#ff4081"/>
                </linearGradient>
              </defs>
            </svg>
          </div>
          <h3 class="card-title">恋爱大师</h3>
          <p class="card-desc">情感咨询 · 恋爱攻略 · 约会秘籍</p>
          <div class="card-meta">
            <span class="tag">SSE</span>
            <span class="tag">流式</span>
          </div>
        </div>
        <div class="card-border"></div>
      </div>

      <!-- Super Agent -->
      <div class="app-card agent-card" @click="goToSuperAgent">
        <div class="card-glow"></div>
        <div class="card-content">
          <div class="card-icon">
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <rect x="3" y="3" width="18" height="18" rx="2" stroke="url(#agent-gradient)" stroke-width="2"/>
              <circle cx="9" cy="10" r="1.5" fill="#00f5ff"/>
              <circle cx="15" cy="10" r="1.5" fill="#00f5ff"/>
              <path d="M8 15h8" stroke="#00f5ff" stroke-width="2" stroke-linecap="round"/>
              <defs>
                <linearGradient id="agent-gradient" x1="3" y1="3" x2="21" y2="21">
                  <stop offset="0%" style="stop-color:#00f5ff"/>
                  <stop offset="100%" style="stop-color:#8b5cf6"/>
                </linearGradient>
              </defs>
            </svg>
          </div>
          <h3 class="card-title">超级智能体</h3>
          <p class="card-desc">多工具协作 · 复杂任务 · 自动化执行</p>
          <div class="card-meta">
            <span class="tag">AGENT</span>
            <span class="tag">多步</span>
          </div>
        </div>
        <div class="card-border"></div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
      <p class="footer-text">POWERED BY <span class="highlight">VUE 3</span> + <span class="highlight">SSE</span></p>
      <div class="status-dot"></div>
      <span class="status-text">SYSTEM ONLINE</span>
    </footer>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goToLoveApp = () => {
  router.push('/love')
}

const goToSuperAgent = () => {
  router.push('/agent')
}
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px 20px;
  position: relative;
  overflow: hidden;
}

/* Background Lines */
.bg-lines {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: 
    repeating-linear-gradient(0deg, transparent, transparent 50px, rgba(0, 245, 255, 0.03) 50px, rgba(0, 245, 255, 0.03) 51px),
    repeating-linear-gradient(90deg, transparent, transparent 50px, rgba(139, 92, 246, 0.03) 50px, rgba(139, 92, 246, 0.03) 51px);
  pointer-events: none;
}

.bg-lines::before {
  content: '';
  position: absolute;
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
  background: radial-gradient(circle, rgba(139, 92, 246, 0.1) 0%, transparent 50%);
  animation: rotate 30s linear infinite;
}

@keyframes rotate {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

/* Hero Section */
.hero-section {
  text-align: center;
  margin-bottom: 60px;
  z-index: 10;
}

.main-title {
  font-size: clamp(2.5rem, 8vw, 5rem);
  font-weight: 900;
  letter-spacing: 0.1em;
  margin-bottom: 20px;
  text-transform: uppercase;
  position: relative;
}

.glitch {
  position: relative;
}

.glitch::before,
.glitch::after {
  content: attr(data-text);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.glitch::before {
  animation: glitch-1 2s infinite linear alternate-reverse;
  color: var(--accent-cyan);
  clip-path: polygon(0 0, 100% 0, 100% 35%, 0 35%);
  z-index: -1;
}

.glitch::after {
  animation: glitch-2 3s infinite linear alternate-reverse;
  color: var(--accent-pink);
  clip-path: polygon(0 65%, 100% 65%, 100% 100%, 0 100%);
  z-index: -1;
}

@keyframes glitch-1 {
  0%, 100% { transform: translate(0); }
  20% { transform: translate(-2px, 2px); }
  40% { transform: translate(2px, -2px); }
  60% { transform: translate(-2px, -2px); }
  80% { transform: translate(2px, 2px); }
}

@keyframes glitch-2 {
  0%, 100% { transform: translate(0); }
  20% { transform: translate(2px, -2px); }
  40% { transform: translate(-2px, 2px); }
  60% { transform: translate(2px, 2px); }
  80% { transform: translate(-2px, -2px); }
}

.title-wu {
  color: var(--accent-cyan);
  text-shadow: var(--glow-cyan);
}

.title-ai {
  color: var(--accent-purple);
  text-shadow: var(--glow-purple);
}

.title-agent {
  color: var(--accent-pink);
  text-shadow: var(--glow-pink);
}

.subtitle {
  font-size: clamp(0.9rem, 2.5vw, 1.2rem);
  color: var(--text-secondary);
  letter-spacing: 0.3em;
  margin-bottom: 30px;
}

.divider {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 15px;
}

.divider-dot {
  width: 8px;
  height: 8px;
  background: var(--accent-cyan);
  border-radius: 50%;
  box-shadow: var(--glow-cyan);
  animation: pulse-glow 2s ease-in-out infinite;
}

.divider-line {
  width: 100px;
  height: 2px;
  background: linear-gradient(90deg, var(--accent-cyan), var(--accent-purple), var(--accent-pink));
}

/* App Cards */
.app-list {
  display: flex;
  gap: 40px;
  flex-wrap: wrap;
  justify-content: center;
  z-index: 10;
}

.app-card {
  position: relative;
  width: 320px;
  padding: 40px 30px;
  background: var(--bg-secondary);
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  overflow: hidden;
}

.card-glow {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  opacity: 0;
  transition: opacity 0.4s;
  pointer-events: none;
}

.love-card .card-glow {
  background: radial-gradient(circle at top, rgba(255, 0, 110, 0.2), transparent 60%);
}

.agent-card .card-glow {
  background: radial-gradient(circle at top, rgba(0, 245, 255, 0.2), transparent 60%);
}

.app-card:hover .card-glow {
  opacity: 1;
}

.app-card:hover {
  transform: translateY(-10px) scale(1.02);
}

.love-card:hover {
  box-shadow: 0 20px 60px rgba(255, 0, 110, 0.3), var(--glow-pink);
}

.agent-card:hover {
  box-shadow: 0 20px 60px rgba(0, 245, 255, 0.3), var(--glow-cyan);
}

.card-border {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 16px;
  pointer-events: none;
}

.love-card .card-border {
  border: 1px solid rgba(255, 0, 110, 0.3);
}

.agent-card .card-border {
  border: 1px solid rgba(0, 245, 255, 0.3);
}

.card-content {
  position: relative;
  z-index: 10;
  text-align: center;
}

.card-icon {
  width: 100px;
  height: 100px;
  margin: 0 auto 25px;
  animation: float 3s ease-in-out infinite;
}

.card-icon svg {
  width: 100%;
  height: 100%;
  filter: drop-shadow(0 0 20px rgba(0, 0, 0, 0.5));
}

.card-title {
  font-size: 1.8rem;
  font-weight: 700;
  margin-bottom: 12px;
  letter-spacing: 0.05em;
}

.love-card .card-title {
  color: var(--accent-pink);
}

.agent-card .card-title {
  color: var(--accent-cyan);
}

.card-desc {
  color: var(--text-secondary);
  font-size: 0.95rem;
  line-height: 1.6;
  margin-bottom: 20px;
}

.card-meta {
  display: flex;
  gap: 10px;
  justify-content: center;
  flex-wrap: wrap;
}

.tag {
  padding: 6px 14px;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.1em;
}

.love-card .tag {
  background: rgba(255, 0, 110, 0.15);
  color: var(--accent-pink);
  border: 1px solid rgba(255, 0, 110, 0.3);
}

.agent-card .tag {
  background: rgba(0, 245, 255, 0.15);
  color: var(--accent-cyan);
  border: 1px solid rgba(0, 245, 255, 0.3);
}

/* Footer */
.footer {
  margin-top: 80px;
  display: flex;
  align-items: center;
  gap: 15px;
  z-index: 10;
}

.footer-text {
  font-size: 0.85rem;
  color: var(--text-secondary);
  letter-spacing: 0.2em;
  text-transform: uppercase;
}

.highlight {
  color: var(--accent-cyan);
  font-weight: 600;
}

.status-dot {
  width: 10px;
  height: 10px;
  background: var(--accent-green);
  border-radius: 50%;
  box-shadow: 0 0 10px var(--accent-green);
  animation: pulse-glow 1.5s ease-in-out infinite;
}

.status-text {
  font-size: 0.75rem;
  color: var(--accent-green);
  font-weight: 600;
  letter-spacing: 0.15em;
}

/* Responsive Design */
@media (max-width: 768px) {
  .home-container {
    padding: 30px 15px;
  }

  .hero-section {
    margin-bottom: 40px;
  }

  .app-list {
    gap: 25px;
  }

  .app-card {
    width: 100%;
    max-width: 320px;
  }

  .footer {
    flex-direction: column;
    gap: 10px;
  }
}

@media (max-width: 480px) {
  .card-icon {
    width: 80px;
    height: 80px;
  }

  .card-title {
    font-size: 1.5rem;
  }
}
</style>
